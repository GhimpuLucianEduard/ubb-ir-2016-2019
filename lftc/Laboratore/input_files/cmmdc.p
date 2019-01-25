start
{
    int a = input;
    int b = input;

    int r = a;

    while (r != 0)
    {
        r = a % b;
        a = b;
        b = r;
    }

    print a;
}
