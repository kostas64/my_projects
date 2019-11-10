//Nikolaos Apostolakis - icsd13012
//Konstantinos Efkarpidis - icsd15051

#include <stdio.h>
#include <stdlib.h>
#include <time.h>


int search_v1 (int *,int); // O(n) poluplokotitas
int search_v2 (int *,int); // O(n^2) poliplokotitas
int find_max(int ,int *,int *);
int *pos; //Deiktis poy dixnei ston arithmo me tis perissoteres emfaniseis

int main()
{

    int i,n,key,*a,times,pos2;
    pos=&pos2;
    printf("Dose megethos pinaka : ");
    scanf("%d",&n);
    printf("\n");
    srand(time(NULL));
    a = (int*)malloc(n*sizeof(int));

    printf("\-----Pinakas pros anazitisi----\n");
    for(i=0; i<n; i++)
    {
        *(a+i) = rand()%11;
        printf(" %d",*(a+i)) ;
    }
    printf("\n-------------------------------");
    printf("\n------------O(n)---------------");
    clock_t start_t, end_t, start_t2,end_t2;
    start_t = clock();
    times = search_v1(a,n);
    end_t = clock();
    printf("\nO arithmos %d emfanizete %d fores se %f",pos2,times, (double)(end_t-start_t)/CLOCKS_PER_SEC);
    printf("\n-------------------------------");
    printf("\n-----------O(n^2)--------------");
    start_t2 = clock();
    times = search_v2(a,n);
    end_t2 = clock();
    printf("\nO arithmos %d emfanizete %d fores se %f\n",pos2,times, (double)(end_t2-start_t2)/ CLOCKS_PER_SEC);

}
int search_v1(int *a,int n)
{
    int j;
    int *array = (int*)calloc(11,sizeof(int)); //Dynamiki desmeysi pinaka kai arxikopoisi me 0
                                               //Kathe thesi toy anaparista ton arithmo poy deixnei i thesi
    for(j=0; j<n; j++){
        *(array + (*(a+j)) )+=1;
    }
    printf("\nArithmoi :");
    printf("\n");
    for(j=0; j<11; j++)
    {
        printf(" %d",j) ;
    }
    printf("\nFores enmfanisis :");
    printf("\n"); //Emfanisi pinaka metriton
    for(j=0; j<11; j++)
    {
        printf(" %d",*(array+j)) ;
    }

    return find_max(n,array,pos);
}

int search_v2(int *a,int n)
{

    int *array = (int*)calloc(11,sizeof(int));
    int i,j;

    for(i=0; i<=10; i++)  //Dipli for -> O(n^2)
    {
        for(j=0; j<n; j++)
        {
            if(i==*(a+j))
                *(array+(*(a+j)))+=1; //H thesi tou pinaka prosdiorizei ton aritho poy metra
        }

    }
    printf("\nArithmoi :");
    printf("\n");
    for(j=0; j<11; j++)
    {
        printf(" %d",j) ;
    }
    printf("\nFores enmfanisis :");
    printf("\n");
    for(i=0; i<11; i++)
    {
        printf(" %d",*(array+i)) ;
    }

    return find_max(n,array,pos);
}

int find_max(int n,int *a,int *pos)
{

    int i,max=0;

    for(i=0; i<11; i++)
    {
        if(max<*(a+i))
        {
            max=*(a+i); //Fores
            *pos = i; //thesi
        }

    }
    return max;

}
