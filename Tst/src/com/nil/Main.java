package com.nil;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
public class Main{
    public static void main(String[] args) {
        B b = new B();
        b.b();
        System.out.println("------------------");
        A a = new B();
        a.visit();
    }
}
class A {
    String a = "a";
    A () {
        System.out.println("a this.a: " + this.a);
    }
    void visit() {
        System.out.println("this.getClass(): " + this.getClass());
    }
}
class B extends A {
    String a = "b";
    B() {
        System.out.println("b super.a: " + super.a);
        System.out.println("b this.a: " + this.a);
        System.out.println("super.equal(this): " + super.equals(this));
    }
    void b() {
        visit();
    }
}


//public class Main {
//
//    public static void main(String[] args) throws Exception {
//        generateSignature("{\"ss\":\"1\",\"msg\":{\"txnAmount\":\"1000\",\"merchantTxnRef\":\"12345678901\",\"b2sTxnEndURL\":\"https://www.baidu.com\",\"s2sTxnEndURL\":\"https://www.baidu.com?success=1\",\"netsMid\":\"UMID_837764000\",\"merchantTxnDtm\":\"2021-08-17 11:25:34.18\",\"cardHolderName\":\"Ah Hoa\",\"cvv\":\"232\",\"expiryDate\":\"2901\",\"pan\":\"4111111111111111\",\"submissionMode\":\"S\",\"paymentType\":\"SALE\",\"paymentMode\":\"CC\",\"currencyCode\":\"SGD\",\"merchantTimeZone\":\"+8:00\",\"netsMidIndicator\":\"U\",\"ipAddress\":\"127.0.0.1\",\"language\":\"en\"}}",
//                "800d2d4e-5e8e-44d1-9ceb-3e09704939f5");
//
//    }
//
//
//
//
//    public static byte[] hashSHA256ToBytes(byte[] input) throws Exception
//    {
//        byte[] byteData = null;
//
//        MessageDigest md = MessageDigest.getInstance("SHA-256");
//        md.update(input);
//
//        byteData = md.digest();
//
//        return byteData;
//    }
//
//    public static String encodeBase64(byte[] data) throws Exception
//    {
//        return DatatypeConverter.printBase64Binary(data);
//    }
//
//    public static String generateSignature(String txnReq,String secretKey) throws Exception{
//        String concatPayloadAndSecretKey = txnReq + secretKey;
//        System.out.println("concatPayloadAndSecretKey-->" + concatPayloadAndSecretKey);
//        String hmac = encodeBase64(hashSHA256ToBytes(concatPayloadAndSecretKey.getBytes()));
//        System.out.println("hmac-->" + hmac);
//        return hmac;
//
//    }
//}
