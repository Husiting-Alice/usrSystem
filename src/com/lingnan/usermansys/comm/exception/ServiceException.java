package com.lingnan.usermansys.comm.exception;

	/*
	 * 自定义异常，
	 * 1、 继承运行时异常
	 * 2、构造方法
	 */
	public class ServiceException extends DaoException{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ServiceException() {
		
		}

		public ServiceException(String arg0) {
			super(arg0);
		}


		public ServiceException(String arg0, Throwable arg1) {
			super(arg0, arg1);
		}

		public ServiceException(Throwable arg0) {
			super(arg0);
		}		

	}


