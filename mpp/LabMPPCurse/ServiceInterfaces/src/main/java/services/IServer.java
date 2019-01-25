package services;

import Models.Cursa.Cursa;
import Models.Destinatie.Destinatie;
import Models.Loc;
import Models.Personal.Personal;
import Models.Rezervare;

public interface IServer {
     void login(Personal personal, IObserver client) throws ServerException;
     Destinatie[] getDestinatii() throws  ServerException;
     Cursa[] getCurse() throws ServerException;
     Loc[] getLocuri(Cursa c) throws ServerException;
     void addRezervare(int idCursa, String nume, String prenume, int nrLocuri) throws ServerException;
//     void sendMessage(Message message) throws ChatException;
//     void logout(User user, IChatObserver client) throws ChatException;
//     User[] getLoggedFriends(User user) throws ChatException;
}
