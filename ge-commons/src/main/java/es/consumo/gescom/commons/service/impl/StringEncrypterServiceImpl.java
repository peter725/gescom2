package es.consumo.gescom.commons.service.impl;

import es.consumo.gescom.commons.exception.AppException;
import es.consumo.gescom.commons.service.StringEncrypterService;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;

/**
 * Clase para el cifrado de cadenas.
 */
@Service
public class StringEncrypterServiceImpl implements StringEncrypterService {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StringEncrypterServiceImpl.class);

    /**
     * Constante ENCRYPTION_SCHEME.
     */
    public static final String ENCRYPTION_SCHEME = "AES";

    /**
     * Constante DEFAULT_ENCRYPTION_KEY.
     */
    public static final String DEFAULT_ENCRYPTION_KEY = "eXhjwW5OrxIdTf8qn5TAUwMK4Ji1wnyE";

    /**
     * Constante DEFAULT_ENCRYPTION_IV.
     */
    public static final String DEFAULT_ENCRYPTION_IV = "fmvT8MFz6nPK0a0R";

    /**
     * Constante DEFAULT_ENCRYPTION_SALT.
     */
    public static final String DEFAULT_ENCRYPTION_SALT = "DQINpgi0";

    /**
     * Constante ENCRYPTION_ERROR_LITERAL.
     */
    private static final String ENCRYPTION_ERROR_LITERAL = "encryption_error";

    /**
     * La clave m key spec.
     */
    private KeySpec mKeySpec;

    /**
     * The m iv spec.
     */
    private GCMParameterSpec mIvSpec;

    /**
     * La clave m key factory.
     */
    private SecretKeyFactory mKeyFactory;

    /**
     * El cifrado m cipher.
     */
    private Cipher mCipher;

    /**
     * Constructor.
     *
     * @throws AppException
     */
    public StringEncrypterServiceImpl() throws AppException {
        this(DEFAULT_ENCRYPTION_KEY, DEFAULT_ENCRYPTION_SALT, DEFAULT_ENCRYPTION_IV);
    }

    /**
     * Instantiates a new string encrypter util.
     *
     * @param pEncryptionKey the encryption key
     * @throws AppException the core exception
     */
    public StringEncrypterServiceImpl(String pEncryptionKey) throws AppException {
        this(pEncryptionKey, DEFAULT_ENCRYPTION_SALT, DEFAULT_ENCRYPTION_IV);
    }

    /**
     * Constructor.
     *
     * @param pEncryptionKey clave de cifrado
     * @param salt           the salt
     * @param iv             the iv
     * @throws AppException the core exception
     */
    public StringEncrypterServiceImpl(String pEncryptionKey, String salt, String iv) throws AppException {
        if (pEncryptionKey == null) {
            throw new IllegalArgumentException("encryption key was null");
        }

        try {
            mKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            mKeySpec = new PBEKeySpec(pEncryptionKey.toCharArray(), salt.getBytes(StandardCharsets.UTF_8), 65536, 256);
            mIvSpec = new GCMParameterSpec(128, iv.getBytes(StandardCharsets.UTF_8)); // 128 bit auth tag length
            mCipher = Cipher.getInstance("AES/GCM/NoPadding");
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage());
            throw new AppException("encryption_error.no_such_algorithm", e);
        } catch (NoSuchPaddingException e) {
            LOGGER.error(e.getMessage());
            throw new AppException("encryption_error.no_such_padding", e);
        }
    }

    /**
     * Metodo de cifrado de una cadena de caracteres.
     *
     * @param pUnencryptedString cadena sin cifrar
     * @return cadena cifrada
     * @throws AppException
     */
    public final String encrypt(String pUnencryptedString) throws AppException {
        if (isBlank(pUnencryptedString)) {
            throw new IllegalArgumentException("unencrypted string was null or empty");
        }

        try {
            SecretKey secret = new SecretKeySpec(mKeyFactory.generateSecret(mKeySpec).getEncoded(), ENCRYPTION_SCHEME);

            mCipher.init(Cipher.ENCRYPT_MODE, secret, mIvSpec);
            byte[] clearText = pUnencryptedString.getBytes(StandardCharsets.UTF_8);
            byte[] cipherText = mCipher.doFinal(clearText);
            return Base64.encodeBase64String(cipherText);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new AppException(ENCRYPTION_ERROR_LITERAL, e);
        }
    }

    /**
     * Metodo para descifrar una cadena de caracteres.
     *
     * @param pEncryptedString cadena cifrada
     * @return cadena descifrada
     * @throws AppException
     */
    public final String decrypt(String pEncryptedString) throws AppException {
        if (isBlank(pEncryptedString)) {
            throw new IllegalArgumentException("encrypted string was null or empty");
        }

        try {
            SecretKey secret = new SecretKeySpec(mKeyFactory.generateSecret(mKeySpec).getEncoded(), ENCRYPTION_SCHEME);

            mCipher.init(Cipher.DECRYPT_MODE, secret, mIvSpec);
            byte[] clearText = Base64.decodeBase64(pEncryptedString);
            byte[] cipherBytes = mCipher.doFinal(clearText);

            var cypherText = bytes2String(cipherBytes);

            return cypherText;

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new AppException(ENCRYPTION_ERROR_LITERAL, e);
        }
    }

    /**
     * Metodo que convierte un array de bytes en su correspondiente cadena de
     * caracteres UNICODE.
     *
     * @param pBytes array de bytes
     * @return cadena de caracteres
     */
    public static String bytes2String(byte[] pBytes) {
        String result = null;

        result = new String(pBytes, StandardCharsets.UTF_8);

        return result;
    }

    /**
     * Metodo que devuelve true si la cadena pasada es nula, o false en caso
     * contrario.
     *
     * @param pStr cadena a comprobar
     * @return true si la cadena pasada es nula, o false en caso contrario
     */
    public static boolean isBlank(String pStr) {
        return (pStr == null || pStr.trim().length() == 0);
    }

}
