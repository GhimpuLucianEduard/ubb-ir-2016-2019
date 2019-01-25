package services;

import Models.Cursa.Cursa;

import java.util.List;

public interface IObserver {
    void newRezervare(Cursa[] curse) throws ServerException;
//     void messageReceived(Message message) throws ChatException;
//     void friendLoggedIn(User friend) throws ChatException;
//     void friendLoggedOut(User friend) throws ChatException;
}
