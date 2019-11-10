//Nikolaos Apostolakis - icsd13012
//Konstantinos Efkarpidis - icsd15051

#include <stdio.h>
#include <stdlib.h>
#include <time.h>


void swap(int *,int,int);
void swap2(int *,int,int,int);
void binary_search(int *,int *,int,int,int,int);

int main()
{
    int pos2=0;
    int *pos=&pos2;

    char repeat;
    do{

    int i,n,*a,choice=0;
    printf("Dose megethos pinaka : ");
    scanf("%d",&n);
    srand(time(NULL));
    a = (int*)malloc(n*sizeof(int));

    do
    {
        printf("Epilexe 1 gia swap pinaka\nEpilexe 2 gia swap 0 kai 1\nEpilexe 3 gia anadromiki diadiki anazitisi");
        scanf("%d",&choice);
    }
    while(choice!=1 && choice!=2 && choice!=3); //elegxos egkyrotitas


    if(choice==1) //swap pinaka
    {
        printf("-----Pinakas prin tin antistrofi----\n");
        for(i=0; i<n; i++)
        {
            *(a+i) =1+ rand()%10;
            printf(" %d",*(a+i)) ;
        }

        swap(a,0,n);
        printf("\n");
        printf("-----Pinakas meta tin antistrofi----\n");
        for(i=0; i<n; i++)
        {
            printf(" %d",*(a+i)) ;
        }
    }
    else if(choice==2) //swap 1 kai 0
    {
        printf("-----Pinakas prin tin antistrofi----\n");
        for(i=0; i<n; i++)
        {
            *(a+i) =rand()%2;
            printf(" %d",*(a+i)) ;
        }

        swap2(a,0,n-1,n); //s_index dixni stin arxi proti fora, l_index sto telos tin proti fora
        printf("\n");
        printf("-----Pinakas meta tin antistrofi----\n");
        for(i=0; i<n; i++)
        {
            printf(" %d",*(a+i)) ;
        }
    }
    else //diadiki anadromiki
    {
        *a = 1 + rand()%3; //akolouthia gia dimiourgia taxinomimenou pinaka
        printf("%d",*a);
        for(i=1; i<n; i++)
        {
            *(a+i) = *(a+i-1) + 1 + rand()%3; //Arxikopoiisi pinaka
            printf(" %d",*(a+i)) ;
        }

        int key = *a + rand()% (*(a+n-1) - *a + 1);
        printf("\nKey = %d\n",key);
        binary_search(a,pos,0,n-1,n,key); //0 -> start index , n-1 -> last index
        if(*pos==0)
            printf("Den vrethike to klidi");
        else
            printf("\nVrethike to klidi %d sthn thesi %d\n",key,*pos+1);

    }

    printf("\nThelete na ksanadokimasete? (y/n)");
    fflush(stdin);
    scanf("%c",&repeat);

    }while(repeat == 'y');
}

void swap(int *a,int index,int n)
{

    int i,swapper;      // swapper = a | a=b | b=swapper

    swapper = *(a+index);
    *(a+index) = *(a+n-1-index);
    *(a+n-1-index) = swapper;



    if(n%2==0)  //Pinakas me ziges thesis
    {
        if(n/2==index + 1)
            return;
    }
    else    //Pinakas me perittes thesis
    {
        if(n/2==index)
            return;
    }
    index++;
    return swap(a,index,n); //Anadromi
}

void swap2(int *a,int s_index,int l_index,int n)
{

    int i,swapper;

    if(*(a+s_index)==0) //an vrei 0 sto simio poy deixnei
        s_index++;
    else if(*(a+s_index)!=0) //an vrei 1 sto simio pou deixnei
    {
        if(*(a+l_index)==0) //elegkse an sto simio pou deixnei o last pointer exei 0 oste na kanei swap
        {
            swapper = *(a+s_index);
            *(a+s_index)=*(a+l_index);
            *(a+l_index)=swapper;
        }
        else
        {
            do
            {
                l_index--; //oso vriski assous sto telos phgene aristera
            }
            while(*(a+l_index)==1 &&(s_index!=l_index));
        }
    }
    printf("\n");
    printf("S_index %d - L_index %d\n",s_index,l_index);
    for(i=0; i<n; i++)
    {
        printf(" %d",*(a+i)) ;
    }

    if(s_index == l_index)  //sunthiki termatismoy
    {
        return;
    }

    return swap2(a,s_index,l_index,n); //anadromi
}

void binary_search(int *a,int *pos,int start,int end,int n,int key)
{

    int middle=(start+end)/2;

    if(start>end) //sunthiki termatismoy
        return;
    else
    {
        if(*(a+middle) == key) //Periptosi pou to klidi einai sti mesi
        {

            *pos = middle;
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
        return binary_search(a,pos,start,end,n,key); //anadromiki diadiki anazitisi
    }

}



