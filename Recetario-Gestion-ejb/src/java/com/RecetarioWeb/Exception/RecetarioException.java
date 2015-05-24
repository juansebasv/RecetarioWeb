/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RecetarioWeb.Exception;

/**
 *
 * @author Personal
 */
public class RecetarioException extends Exception {

    /**
     * Creates a new instance of <code>RecetarioException</code> without detail
     * message.
     */
    public RecetarioException() {
    }

    /**
     * Constructs an instance of <code>RecetarioException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public RecetarioException(String msg) {
        super(msg);
    }
}
