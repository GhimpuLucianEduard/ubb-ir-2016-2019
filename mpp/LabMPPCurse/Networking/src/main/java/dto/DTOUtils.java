package dto;

import Models.Cursa.Cursa;
import Models.Destinatie.Destinatie;
import Models.Loc;
import Models.Personal.Personal;

public class DTOUtils {


    public static Personal getFromDTO(PersonalDTO persdto){

        String user=persdto.getUserName();
        String pass=persdto.getPass();
        return new Personal(user,pass);

    }
    public static PersonalDTO getDTO(Personal pers){

        String user = pers.getUserName();
        String pass= pers.getPass();
        return new PersonalDTO(user,pass);
    }

    public static Destinatie getFromDTO(DestinatieDTO var){
        return new Destinatie(var.getId(),var.getNume());
    }

    public static DestinatieDTO getDTO(Destinatie var){
        return new DestinatieDTO(var.getId(),var.getNume());
    }

    public static DestinatieDTO[] getDTO(Destinatie[] var){
        DestinatieDTO[] frDTO=new DestinatieDTO[var.length];
        for(int i=0;i<var.length;i++)
            frDTO[i]=getDTO(var[i]);
        return frDTO;
    }

    public static Destinatie[] getFromDTO(DestinatieDTO[] var){
        Destinatie[] friends=new Destinatie[var.length];
        for(int i=0;i<var.length;i++){
            friends[i]=getFromDTO(var[i]);
        }
        return friends;
    }

    public static Cursa getFromDTO(CursaDTO var){
        return new Cursa(var.getId(),var.getIdDestinatie(),var.getData(), var.getLocatiePlecare(),var.getNrLocuriDisponibile());
    }

    public static CursaDTO getDTO(Cursa var){
        return new CursaDTO(var.getId(),var.getIdDestinatie(),var.getData(), var.getLocatiePlecare(),var.getNrLocuriDisponibile());
    }

    public static CursaDTO[] getDTO(Cursa[] var){
        CursaDTO[] frDTO=new CursaDTO[var.length];
        for(int i=0;i<var.length;i++)
            frDTO[i]=getDTO(var[i]);
        return frDTO;
    }

    public static Cursa[] getFromDTO(CursaDTO[] var){
        Cursa[] friends=new Cursa[var.length];
        for(int i=0;i<var.length;i++){
            friends[i]=getFromDTO(var[i]);
        }
        return friends;
    }

    public static Loc getFromDTO(LocDTO var){
        return new Loc(var.getNrCurent(),var.getNumeClient(),var.getPrenumeClient());
    }

    public static LocDTO getDTO(Loc var){
        return new LocDTO(var.getNrCurent(),var.getNumeClient(),var.getPrenumeClient());
    }

    public static LocDTO[] getDTO(Loc[] var){
        LocDTO[] frDTO=new LocDTO[var.length];
        for(int i=0;i<var.length;i++)
            frDTO[i]=getDTO(var[i]);
        return frDTO;
    }

    public static Loc[] getFromDTO(LocDTO[] var){
        Loc[] friends=new Loc[var.length];
        for(int i=0;i<var.length;i++){
            friends[i]=getFromDTO(var[i]);
        }
        return friends;
    }

}
