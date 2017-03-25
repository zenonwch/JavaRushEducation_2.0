package com.javarush.task.task32.task3208;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/* 
RMI-2
*/
public class Solution {
    public static Registry registry;

    //pretend we start rmi client as CLIENT_THREAD thread
    public static final Thread CLIENT_THREAD = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                for (final String bindingName : registry.list()) {
                    final Animal service = (Animal) registry.lookup(bindingName);
                    service.printName();
                    service.say();
                }
            } catch (final RemoteException e) {
                e.printStackTrace();
            } catch (final NotBoundException e) {
                e.printStackTrace();
            }
        }
    });

    //pretend we start rmi server as SERVER_THREAD thread
    public static final Thread SERVER_THREAD = new Thread(new Runnable() {
        @Override
        public void run() {
            //напишите тут ваш код
            try {
                registry = LocateRegistry.createRegistry(2099);
                final Cat cat = new Cat("Barsik");
                final Dog dog = new Dog("Muhtar");

                final Remote stubCat = UnicastRemoteObject.exportObject(cat, 0);
                final Remote stubDog = UnicastRemoteObject.exportObject(dog, 0);

                registry.bind("server.cat", stubCat);
                registry.bind("server.dog", stubDog);

            } catch (final RemoteException | AlreadyBoundException e) {
                e.printStackTrace();
            }
        }
    });

    public static void main(final String[] args) throws InterruptedException {
        //start rmi server thread
        SERVER_THREAD.start();
        Thread.sleep(1000);
        //start client
        CLIENT_THREAD.start();
    }
}