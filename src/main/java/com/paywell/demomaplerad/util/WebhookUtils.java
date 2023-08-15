package com.paywell.demomaplerad.util;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class WebhookUtils {

    @Value("${webhook.secret-key}")
    private static String webhookSecretKey;

    Gson gson = GsonSingleton.getInstance();
    private static final String PREFIX = "v1,";
    private static final String DELIMITER = "[,;:\\s/]+";

    public static boolean isSignatureMatching(List<String> svixSignature, String signatureReceived) {

        List<String> cleanedSignatures = new ArrayList<>();

        for (String signature : svixSignature) {
            String cleanedSignature = removePrefixAndDelimiter(signature);
            cleanedSignatures.add(cleanedSignature);
        }

        for (String signature : cleanedSignatures){
            if (secureCompare(signature, signatureReceived)){
                return true;
            }
        }
        return false;
    }

    private static boolean secureCompare(String signature, String signatureReceived) {
        if (signature.length() != signatureReceived.length()) {
            return false;
        }

        int result = 0;
        for (int i = 0; i < signature.length(); i++) {
            result |= signature.charAt(i) ^ signatureReceived.charAt(i);
        }

        return result == 0;
    }

    private static String removePrefixAndDelimiter(String signature) {
        if (signature.startsWith(PREFIX)){
            signature = signature.substring(PREFIX.length());
        }

        Pattern pattern = Pattern.compile(DELIMITER);
        Matcher matcher = pattern.matcher(signature);
        signature = matcher.replaceAll("");

        return signature;
    }


    public static byte[] convertKeyToBase64(String secretKey) {
        return Base64.getDecoder().decode(secretKey);
    }

    public static String getSignature(String svixId, String svixTimestamp, String body){

        String signedContent = svixId.concat(".").concat(svixTimestamp).concat(".").concat(body);

        String keyWithoutPrefix = webhookSecretKey.split("_")[1];

        byte[] secretKeyInBytes = convertKeyToBase64(keyWithoutPrefix);

        return createSignature(secretKeyInBytes, signedContent);
    }


    public static String createSignature(byte [] secretKeyInBytes, String signedContent) {
        try {
            Mac hmacSha256 = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyInBytes, "HmacSHA256");
            hmacSha256.init(secretKeySpec);

            byte[] signatureBytes = hmacSha256.doFinal(signedContent.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(signatureBytes);

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verifySignatureMatch(HttpServletRequest request, Object eventPayload){
        String svixId = request.getHeader("svix-id");

        String svixTimestamp = request.getHeader("svix-timestamp");

        List<String> svixSignature = Collections.singletonList(request.getHeader("svix-signature"));


        String webhook = gson.toJson(eventPayload);

        String signature = getSignature(svixId, svixTimestamp, webhook);

        return isSignatureMatching(svixSignature, signature);
    }

}
