//Nikolaos Apostolakis - icsd13012
//Konstantinos Efkarpidis - icsd15051

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int pos2=0;
int *pos=&pos2;
void binary_search(int *,int,int,int,int);

int main(){

    int i,n,*a;


    printf("Dose megethos pinaka : ");
    scanf("%d",&n);

    srand(time(NULL));

    a = (int*)malloc(n*sizeof(int));
    *a = 1 + rand()%3; //akolouthia gia dimiourgia taxinomimenou pinaka
    for(i=1; i<n; i++)
    {
        *(a+i) = *(a+i-1) + 1 + rand()%3; //Arxikopoiisi pinaka
        printf(" %d",*(a+i)) ;
    }

    int key = *a + rand()% (*(a+n-1) - *a + 1);
    printf("\nKey = %d\n",key);
    binary_search(a,0,n-1,n,key); //0 -> start index , n-1 -> last index
    if(*pos==0)
        printf("Den vrethike to klidi");
    else
    printf("\nVrethike to klidi %d sthn thesi %d",key,*pos);

}

void binary_search(int *a,int start,int end,int n,int key){

    int middle=(start+end)/2;

    if(start>end) //sunthiki termatismoy
        return;
    else{
        if(*(a+middle) == key)
        {
            pos = &middle;
            return;
        }
        else if(*(a+middle) < key)
        {
            start=middle+1;

        }
        else
        {
            end=middle-1;

        }
            return binary_search(a,start,end,n,key); //anadromiki diadiki anazitisi
    }

}
