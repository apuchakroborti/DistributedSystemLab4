/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_server;

//import javarmi.RmiServer.*;
import Rmi_Interface.MethodInterface;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author apu
 */
public class ImplementSecurity implements MethodInterface{
    //private int SocketPort;
    //private String FILE_TO_SEND;
    //private FileInputStream fis;
    //private BufferedInputStream bis;
    //private OutputStream os;
    //private ServerSocket servsock;
    //private Socket sock;
    private String encryptFile;
    private String cipherEnFile;
    private String vigenereEnFile;
    //private String cipherDeFile;
    //private String vigenereDeFile;
    private String mainFile;
    
    //
    private char msg[];
    private char newKey[];
    private char encryptedMsg[];
    private char decryptedMsg[];
    //
    private char alphabet[]={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    private char newAlphabet[];		
    //

    public ImplementSecurity(String fileName) {
    encryptFile=null;
        cipherEnFile="cipher_en_file1.txt";
        //cipherDeFile="cipher_de_file.txt";
        vigenereEnFile="vigenere_en_file1.txt";
        //vigenereDeFile="vigenere_de_file.txt";
        mainFile=fileName;
    }
    
    public String CaesarCipher(){
        String result="";
        try {
            result=caesarEncryption();
            prnt(result+"\n");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImplementSecurity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImplementSecurity.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*try {
            //result=caesarDecryption();
        } catch (IOException ex) {
            Logger.getLogger(ImplementSecurity.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        finally{
            return result;
        }
    }
    
    public String caesarEncryption() throws FileNotFoundException, IOException
    {
                //BufferedReader br = new BufferedReader(new FileReader("cipher.txt"));
                int alphabetLength=alphabet.length;
                prnt("length:"+alphabetLength+"\n");
                char[] newAlphabet1=new char[alphabetLength];
                //char key[] = {'G','O','R','D','I','A','N'};
                //char oldKey[] = {'A','B','B','A','S'};
                char oldKey[] = {'B','A','T','T','L','E','S'};
                int oldKeyLength=oldKey.length;
                prnt("oldKeyLength:"+oldKeyLength+"\n");
                String s="";
                for(int i=0;i<oldKeyLength;i++)
                {
                    if(!s.contains(""+oldKey[i]) && oldKey[i]>='A' && oldKey[i]<='Z'){
                        s=s+oldKey[i];
                    }
                }
                prnt("s:"+s+"\n");
                char key[]=s.toCharArray();
                int keyLength=key.length;
		prnt("KeyLength:"+keyLength+"\n");
                String newAlpha="";
                for(int i=0;i<keyLength;i++)
                {
                    newAlphabet1[i]=key[i];
                    newAlpha=newAlpha+newAlphabet1[i];
                }
                
                for(int i=0,j=0;i<alphabetLength;i++)
                {
                    if(!newAlpha.contains(alphabet[i]+"")){
                        newAlphabet1[keyLength+j]=alphabet[i];
                        newAlpha=newAlpha+alphabet[i];
                        j++;
                    }
                }
                prnt(newAlpha.toString()+"\n");
        String res=caesarDecryption(newAlpha);
        prnt("Result in server:"+res+"\n");
        return res;
                /*
                //Scanner input=new Scanner(new FileReader(mainFile));
                FileReader fr=new FileReader(mainFile);
                StringBuilder cae_str=new StringBuilder();
                int position=0;
                while((position=fr.read())!=-1)
                {
                    char ch=(char)position;
                    prnt("Position:"+position+"\n");
                    prnt("Char:"+ch+"\n");
                    if(ch>'A'){
                        cae_str.append(newAlphabet[ch-'A']);
                    }
                    
                }
                
                fr.close();
                prnt("Encoded:"+cae_str.toString()+"\n");
    		//PrintWriter file=new PrintWriter(cipherEnFile);
                //file.write(cae_str.toString());
                //file.close();
		*/
 }
    //ascii of A=65
    //key={GORDIANBCEFHJKLMPQSTUVWXYZ}
    public String caesarDecryption(String newAlpha) throws FileNotFoundException, IOException
    {
        char[] newAlphabet1=newAlpha.toCharArray();
        //prnt("new alphabet length:"+newAlphabet1.length+"\n");
                //BufferedReader br = new BufferedReader(new FileReader("cipher.txt"));
                //Scanner input=new Scanner(new FileReader(cipherEnFile));
                //FileReader fr=new FileReader(cipherEnFile);
                //File file=new File("new.txt");
                
                StringBuilder de_str=new StringBuilder();
                //Scanner input=new Scanner(new FileReader("new.txt"));
                //BufferedReader input = new BufferedReader(new FileReader("new.txt"));
                //int position=0;
                //input.read()
                char ch;
                //char ch=(char) input.read();
                //String data="SHOWMETHEMEANINGOFBEINGLONELYRGNWLDTGDLDAMHMFNEBDHMFKNMDKY";
                String data="HKMWRDYQEISHKMWRDY";
                char datach[]=data.toCharArray();
                prnt("datachLength:"+datach.length+"\n");
                
                for(int i=0;i<datach.length;i++)
                {
                    ch=datach[i];
                    prnt("ch:"+ch+"\n");
                    int k=0;
                    for(int j=0;j<newAlphabet1.length;j++){
                        if(ch==newAlphabet1[j]){
                            k=j;
                            prnt("k:"+k+"\n");
                            break;
                        }
                    }
                    
                    de_str.append(alphabet[k]);
                    prnt("alpha at k:"+alphabet[k]+"\n");
                }
                prnt("\nend\n");
                /*
                while((ch=(char)input.read())>0)
                {
                    //char ch=(char)input.nextByte();
                    
                    prnt("Ch:"+ch+"\n");
                    int i=0;
                    for(i=0;i<newAlphabet1.length;i++)if(ch==newAlphabet1[i])break;
                    
                    de_str.append(alphabet[i]);
                }*/
                /*FileReader fr=new FileReader(new File("new.txt"));
                StringBuilder de_str=new StringBuilder();
                int position=0;
                while((position=fr.read())!=-1)
                {
                    char ch=(char)position;
                    
                    prnt("Ch:"+ch+"\n");
                    int i=0;
                    for(i=0;i<newAlphabet1.length;i++)if(ch==newAlphabet1[i])break;
                    
                    de_str.append(alphabet[i]);
                }*/
                
                prnt(de_str.toString()+"\n");
                String r=de_str.toString();
                return r;
                /*byte[] myByteArray=de_str.toString().getBytes();
                os=sock.getOutputStream();
                os.write(myByteArray,0, de_str.length());
                os.flush();
                prnt("Done... \n");
                os.close();
                if(sock!=null)sock.close();*/
                
                
                
                
    }
    public void vigenereEncryption() throws FileNotFoundException, IOException
    {
                //BufferedReader br = new BufferedReader(new FileReader("cipher.txt"));
                //Scanner input=new Scanner(new FileReader(mainFile));
                FileReader fr=new FileReader(mainFile);
                StringBuilder str=new StringBuilder();
                int position=0;
                while((position=fr.read())!=-1){
                    char ch=(char)position;
                    str.append(ch);
                }
                int length=str.length();
                prnt("Builder string length:"+length+"\n");
                fr.close();
                //while(input.hasNext())
                //{
                  //  char ch=(char)input.nextByte();
                    //str.append(ch);
                //}
                String data=str.toString();
                
                prnt("Main File data:"+data+"\n");
                msg=new char[length];
                for(int i=0;i<length;i++)
                {
                    msg[i]=data.charAt(i);
                    prnt("=>"+msg[i]+"<=");
                }
                prnt("\n");
                //msg=data.toCharArray();
                prnt("Main message length:"+msg.length+"\n");
                prnt("Main File data msg:"+msg.toString()+"\n");
                //char msg[] = {'T','H','E','J','A','V','A','P','R','O','G','R','A','M','M','E','R'};
		//char key[] = {'G','O','R','D','I','A','N'};
                char key[] = {'A','B','S'};
		int msgLen = msg.length, i, j;
		
		newKey= new char[msgLen];
		encryptedMsg= new char[msgLen];
		//decryptedMsg= new char[msgLen];
		
		//generate new key in cyclic manner equal to the length of original message
		for(i = 0, j = 0; i < msgLen; ++i, ++j){
			if(j == key.length){
                            j = 0;
                        }
			newKey[i] = key[j];
                        prnt("=>"+newKey[i]+"<=");
		}
                //newKey[i]='\0';
                prnt("\n");
		 PrintWriter file=new PrintWriter(vigenereEnFile);
		//encryption
		for(i = 0; i < msgLen; ++i){
			encryptedMsg[i] = (char)(((msg[i] + newKey[i]) % 26) + 'A');
                        prnt("=>"+encryptedMsg[i]+"<=");
                        file.write(encryptedMsg[i]+"");
                }
                //encryptedMsg[i]='\0';
                prnt("\n");
                //prnt("encrypted message:"+encryptedMsg.toString()+"\n");
                //prnt("encypted message length"+encryptedMsg.length+"\n");
                //String s=encryptedMsg.toString();
                //prnt("s:"+s+"\n");
               
                
                file.close();
    }
    public String vigenereDecryption() throws FileNotFoundException, IOException
    {
                //BufferedReader br = new BufferedReader(new FileReader("cipher.txt"));
                //Scanner input=new Scanner(new FileReader(vigenereEnFile));
                StringBuilder en_str=new StringBuilder();
                //while(input.hasNext())
                //{
                  //  char ch=(char)input.nextByte();
                   // en_str.append(ch);
                //}
                FileReader fr=new FileReader(vigenereEnFile);
                StringBuilder str=new StringBuilder();
                int position=0;
                while((position=fr.read())!=-1){
                    char ch=(char)position;
                    en_str.append(ch);
                }
                fr.close();
                int length=en_str.length();
                prnt("de length:"+length+"\n");
                char en_msg[]=new char[length];
                String en_data=en_str.toString();
                prnt("en data:"+en_data+"\n");
                for(int i=0;i<length;i++)
                {
                    en_msg[i]=en_data.charAt(i);
                }
                //char en_msg[]=en_str.toString().toCharArray();
                //char msg[] = {'T','H','E','J','A','V','A','P','R','O','G','R','A','M','M','E','R'};
		//char key[] = {'G','O','R','D','I','A','N'};
		int msgLen = en_msg.length, i, j;
		
		//newKey = new char[msgLen];
		//encryptedMsg= new char[msgLen];
		decryptedMsg= new char[msgLen];
		String result="";
                for(i = 0; i < msgLen; ++i)
                {
			decryptedMsg[i] = (char)((((en_msg[i] - newKey[i]) + 26) % 26) + 'A');
                        result=result+decryptedMsg[i];
                }
                
                //prnt("Decrypted message:"+decryptedMsg.toString()+"\n");
                return result;
                /*byte[] myByteArray=decryptedMsg.toString().getBytes();
                os=sock.getOutputStream();
                os.write(myByteArray,0, decryptedMsg.toString().length());
                os.flush();
                prnt("Done... \n");
                os.close();
                if(sock!=null)sock.close();*/
    }
    
    
    public String VigenereCipher(){
        String result="";
        try {
            vigenereEncryption();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImplementSecurity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImplementSecurity.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            result=vigenereDecryption();
        } catch (IOException ex) {
            Logger.getLogger(ImplementSecurity.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            return result;
        }
        
    }
    
    public void prnt(String str){System.out.print(str);}
    
    //public void prnt(String str){System.out.print(str);}
    
}
