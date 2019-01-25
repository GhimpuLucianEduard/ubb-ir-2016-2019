public class NrComplex
{
    private float parteaReala;
    private float parteaImaginara;


    public NrComplex(float parteaReala, float parteaImaginara)
    {
        this.parteaReala = parteaReala;
        this.parteaImaginara = parteaImaginara;
    }

    public float getParteaReala()
    {
        return parteaReala;
    }

    public void setParteaReala(float parteaReala)
    {
        this.parteaReala = parteaReala;
    }

    public float getParteaImaginara()
    {
        return parteaImaginara;
    }

    public void setParteaImaginara(float parteaImaginara)
    {
        this.parteaImaginara = parteaImaginara;
    }

    @Override
    public String toString() {
        return parteaReala+""+parteaImaginara+"i";
    }

    void add(NrComplex nr2)
    {
        parteaReala+=nr2.parteaReala;
        parteaImaginara+=nr2.parteaImaginara;
    }

    void mult(NrComplex nr2)
    /**
     * (ac-bd) + i(bc+ad)
     */
    {
        parteaReala=parteaReala*nr2.parteaReala
        parteaImaginara-=nr2.parteaImaginara;
    }




}
