/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Sarah Skanes
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.abopu.recipebook.common.exception;

import static com.abopu.recipebook.common.exception.DaoException.ErrorCode.EXCEPTION;

/**
 * @author Sarah Skanes
 * @created October 30, 2016.
 */
public class DaoException extends Exception {
	
	private ErrorCode reason;
	
	/***************************************************************************
	 *
	 * Constructors
	 *
	 **************************************************************************/
	
	
	public DaoException(ErrorCode reason) {
		setReason(reason);
	}

	public DaoException(String message, ErrorCode reason) {
		super(message);
		setReason(reason);
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
		setReason(EXCEPTION);
	}

	public DaoException(Throwable cause) {
		super(cause);
		setReason(EXCEPTION);
	}

	public DaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		setReason(EXCEPTION);
	}
	
	
	
	/***************************************************************************
	 *
	 * Public API
	 *
	 **************************************************************************/

	public ErrorCode getReason() {
		return reason;
	}

	public void setReason(ErrorCode reason) {
		this.reason = reason;
	}

	public DaoException withReason(ErrorCode reason) {
		setReason(reason);
		return this;
	}



	/***************************************************************************
	 *
	 * Error Codes
	 *
	 **************************************************************************/
	
	public enum ErrorCode {
		INTEGRITY_VIOLATION,
		EXCEPTION
	}
}
