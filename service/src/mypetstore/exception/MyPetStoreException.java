package mypetstore.exception;

/**
 * 
 * MyPetStore基础异常类
 * 
 * @author zhou wei
 * @version 1.0
 * @since JDK1.5
 */
public class MyPetStoreException extends RuntimeException {
	/**
	 * 根据指定的错误消息构造MyPetStoreException异常.
	 * 
	 * @param msg
	 *            异常消息
	 */
	public MyPetStoreException(String msg) {
		super(msg);
	}

	/**
	 * 
	 * 根据指定的错误消息和根异常构造MyPetStoreException异常.
	 * 
	 * @param msg
	 *            异常消息
	 * @param cause
	 *            根异常
	 */
	public MyPetStoreException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
