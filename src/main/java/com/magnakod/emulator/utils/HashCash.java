package com.magnakod.emulator.utils;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCash {
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public String solveChallenge(String prefix,int length) throws NoSuchAlgorithmException {
//        String prefix = "97668EEFE2B31548D9680BBF2DD2E236";
//        int length = 20;
        byte[] seed = new byte[8];
        byte[] loginContextDigest = MessageDigest.getInstance("SHA1").digest();
        System.arraycopy(loginContextDigest, 12, seed, 0, 8);

        byte[] new_prefix = hexStringToByteArray(prefix); // fix conv str to byte array
        return solveHashCash(new_prefix, length, seed);
    }

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    private static byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }

    private static String solveHashCash(byte[] prefix, int length, byte[] random) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA1");

        ByteBuffer rnd = ByteBuffer.wrap(random);

        Long l1 = rnd.getLong();
        Long l2 = Long.valueOf(0);

        while (true) {
            md.reset();
            md.update(prefix);
            md.update(longToBytes(l1));
            md.update(longToBytes(l2));
            byte[] digest = md.digest();

            ByteBuffer buf = ByteBuffer.wrap(digest);
            Long bufLong = buf.getLong(12); //  [12:20]

            if (Long.numberOfTrailingZeros(bufLong) >= length) { // CTZ
                return bytesToHex(longToBytes(l1)) + bytesToHex(longToBytes(l2));
            }

            l1++;
            l2++;
        }
    }

}