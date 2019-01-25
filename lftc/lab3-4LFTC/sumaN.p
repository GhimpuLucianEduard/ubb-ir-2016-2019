start
{
    int nr = input;
    array int (nr) nrs;
    int suma = 0;
    int cond = nr;
    while(nr-1 != 0)
    {
        nrs(nr-1) = input;
        nr = nr - 1;
    }
    while (cond != 0)
    {
        suma = suma + nrs(cond);
        cond = cond - 1;
    }

    print suma + 1 + suma;
}