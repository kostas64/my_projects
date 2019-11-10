//Nikolaos Apostolakis - icsd13012
//Konstantinos Efkarpidis - icsd15051

#include <stdio.h>
#include <stdlib.h>
#include <time.h>


int serial_search(int *,int,int);
int binary_search(int *,int *,int,int);

int main()
{

    int i,n,*a,counter; //*counter = plithos sugkriseon diadikis anazitisis

    printf("Dose megethos pinaka : ");
    scanf("%d",&n);

    srand(time(NULL));

    a = (int*)malloc(n*sizeof(int));

    *a = 1 + rand()%3; //Arxikopoiisi protou keliou
    printf("\n-----------SIRIAKH----------\n");
    printf("%d",*a);

    for(i=1; i<n; i++)
    {
        *(a+i) = *(a+i-1) + 1 + rand()%3; //Arxikopoiisi pinaka
        printf(" %d",*(a+i)) ;
    }

    int key = *a + rand()% (*(a+n-1) - *a + 1); //eyresi tyxaioy kleidioy sto
    printf("\nKey = %d \n",key);                                            //euros toy 1ou kai teleytaioy keliou
    int pos = serial_search(a,key,n);
    if(pos==-1)
        printf("\nDen vrethike to klidi, arithmos sugkriseon %d\n",n);
    else
        printf("\nVrethike to klidi = %d , stin thesi %d & plithos sigkriseon %d\n",key,pos,pos);

    printf("----------------------------\n");
    printf("-----------DIADIKH----------\n");
    counter=0;
    int pos2 = binary_search(a,&counter,key,n);
    if(pos==-1)
        printf("\nDen vrethike to klidi, arithmos sugkriseon %d\n",counter);
    else
        printf("\nVrethike to klidi = %d , stin thesi %d & plithos sigkriseon %d\n",key,pos,counter);

}

int serial_search(int *a,int key,int n)
{
    int i;

    for(i=0; i<n; i++)
    {
        if(*(a+i) == key) //elekse siriaka
        {
            return i+1;
        }
    }
    return -1;
}

int binary_search(int *a,int *counter,int key, int n)
{
    int i,pos = -1,start,middle,end;
    start=0; //arxikopoiisi deikton gia diadiki
    end=n-1;

    for(i=0; i<n; i++)
    {
        printf("%d ",*(a+i)) ;
    }

    while(start<=end)
    {

        middle=(start+end)/2;

        if(*(a+middle) == key)
        {
            (*counter)++; //Aparaitites oi parenethesis , metritis sygkriseon
            pos = middle;
            break;
        }
        else if(*(a+middle) < key) //psakse dexio miso tou pinaka
        {
            start=middle+1;
            *counter+=2;
        }
        else //psakse aristero miso tou pinaka
        {
            end=middle-1;
            *counter+=3;
        }
    }
    return pos;

}


