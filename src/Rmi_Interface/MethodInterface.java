/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rmi_Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author apu
 */
public interface MethodInterface extends Remote{
    public String CaesarCipher() throws RemoteException;
    public String VigenereCipher() throws RemoteException;
}
