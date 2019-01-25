import java.util.Arrays;

public class SortingTask extends Task
{
    private int[] numere;

    public SortingTask(int taskID, String descriere, int[] numere)
    {
        super(taskID, descriere);
        this.numere = numere;
    }



    int partition(int[] numere, int left, int right)
    {
        int i = left, j = right, aux;
        int pivot = this.numere[(left + right) / 2];

        while (i <= j)
        {
            while (this.numere[i] < pivot)
            {
                i++;
            }

            while (this.numere[j] > pivot)
            {
                j--;
            }

            if (i <= j)
            {
                aux = this.numere[i];
                this.numere[i] = this.numere[j];
                this.numere[j] = aux;
                i++;
                j--;
            }
        }

        return i;
    }

    void quickSort(int[] numere, int left, int right)
    {
        int index = partition(this.numere, left, right);
        if (left < index - 1)
        {
            quickSort(this.numere, left, index - 1);
        }

        if (index < right)
        {
            quickSort(this.numere, index, right);
        }

    }

//    @Override
//    public String toString() {
//        return numere.toString();
//    }

    @Override
    public void execute()
    {
        quickSort(numere,0,numere.length-1);
        for(int i=0;i<numere.length;i++)
        {
            System.out.print(numere[i]+" ");
        }
        System.out.println(" ");
    }
}
