//Nikolaos Apostolakis - icsd13012
//Konstantinos Efkarpidis - icsd15051

#include <stdio.h>
#include <stdlib.h>
#include <time.h>


int serial_search(int *ar,int key,int n);

int main()
{

    int i,n,key,*ar; //key -> klidi anazitisis
    printf("Dose megethos N:");
    scanf("%d",&n);

    srand(time(NULL)); //arxikopoiisi gennitrias
    key = 1 + rand()%n;
    printf("Key = %d\n",key);
    ar = (int*)malloc(n*sizeof(int)); //Dynamiki desmeusi mnimis

    printf("-----Pinakas pros anazitisi----\n");
    for(i=0; i<n; i++)
    {
        *(ar+i) = 1 + rand()%n;
        printf("%d ",*(ar+i));
    }
    printf("\n-------------------------------");
    int pos = serial_search(ar,key,n); //Arxikopoisi thesis kata tin klisi
    //synartisis anazitisis
    if(pos==-1)
        printf("\nDen vrethike to klidi, arithmos sugkriseon %d",n);
    else
        printf("\nVrethike to klidi = %d , stin thesi %d & plithos sigkriseon %d\n",key,pos,pos);

}

int serial_search(int *ar,int key,int n)
{
    int i;

    for(i=0; i<n; i++)
    {
        if(*(ar+i)==key)  //elegxos keliou gia tautopoiisi
        {
            return i+1;
        }
    }
    return -1; //Periptosi mi euresis klidioy
}
