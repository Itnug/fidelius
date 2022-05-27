package in.projecteka.fidelius;

import in.projecteka.fidelius.dercyprion.DecryptionController;
import in.projecteka.fidelius.dercyprion.DecryptionRequest;
import in.projecteka.fidelius.dercyprion.DecryptionResponse;
import in.projecteka.fidelius.encryotion.EncryptionController;
import in.projecteka.fidelius.encryotion.EncryptionRequest;
import in.projecteka.fidelius.encryotion.EncryptionResponse;
import in.projecteka.fidelius.keys.KeyMaterial;
import in.projecteka.fidelius.keys.KeysController;

//@SpringBootApplication
public class FideliusApplication {

    public static void main(String[] args) throws Exception {
        switch(args[0]) {
            case "e":
            case "encryption": {
                int i = 0;
                final String receiverPublicKey = args[++i];
                final String receiverNonce = args[++i];
                final String senderPrivateKey = args[++i];
                final String senderPublicKey = args[++i];
                final String senderNonce = args[++i];
                final String plainTextData = args[++i];
                final EncryptionRequest encryptionRequest = new EncryptionRequest(receiverPublicKey, receiverNonce, senderPrivateKey, senderPublicKey, senderNonce, plainTextData);
                EncryptionResponse encrypt = new EncryptionController().encrypt(encryptionRequest);
                System.out.println("encryptedData: " + encrypt.getEncryptedData());
                System.out.println("keyToShare: " + encrypt.getKeyToShare());
                break;
            }
            case "d":
            case "decryption": {
                int i = 0;
                final String receiverPrivateKey = args[++i];
                final String receiverNonce = args[++i];
                final String senderPublicKey = args[++i];
                final String senderNonce = args[++i];
                final String encryptedData = args[++i];

                DecryptionRequest decryptionRequest = new DecryptionRequest(receiverPrivateKey, receiverNonce, senderPublicKey, senderNonce, encryptedData);
                DecryptionResponse decrypt = new DecryptionController().decrypt(decryptionRequest);
                System.out.println("decryptedData: " + decrypt.getDecryptedData());
                break;
            }
            case "k":
            case "keys-generate": {
                KeyMaterial keyMaterial = new KeysController().generate();
                System.out.println("publicKey: " + keyMaterial.getPublicKey());
                System.out.println("privateKey: " + keyMaterial.getPrivateKey());
                System.out.println("nonce: " + keyMaterial.getNonce());
                break;
            }
            default:
                throw new IllegalArgumentException("unknown command");
        }
    }

}
