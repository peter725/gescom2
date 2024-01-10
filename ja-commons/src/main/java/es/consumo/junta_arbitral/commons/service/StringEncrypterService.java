package es.consumo.junta_arbitral.commons.service;

public interface StringEncrypterService {
    /**
     * Metodo de cifrado de una cadena de caracteres.
     *
     * @param pUnencryptedString cadena sin cifrar
     * @return cadena cifrada
     */
    String encrypt(String pUnencryptedString);



    /**
     * Metodo para descifrar una cadena de caracteres.
     *
     * @param pEncryptedString cadena cifrada
     * @return cadena descifrada
     */
    String decrypt(String pEncryptedString);


}
