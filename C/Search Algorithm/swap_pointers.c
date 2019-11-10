//Nikolaos Apostolakis - icsd13012
//Konstantinos Efkarpidis - icsd15051

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void swap(int *,int,int,int);

int main()
{

    int i,n,*a;
    printf("Dose megethos pinaka : ");
    scanf("%d",&n);
    srand(time(NULL));
    a = (int*)malloc(n*sizeof(int));

    printf("\-----Pinakas prin tin antistrofi----\n");
    for(i=0; i<n; i++)
    {
        *(a+i) =rand()%2;
        printf(" %d",*(a+i)) ;
    }

    swap(a,0,n-1,n); //s_index dixni stin arxi proti fora, l_index sto telos tin proti fora
    printf("\n");
    printf("\-----Pinakas meta tin antistrofi----\n");
    for(i=0; i<n; i++)
    {
        printf(" %d",*(a+i)) ;
    }

}

void swap(int *a,int s_index,int l_index,int n)
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
        }else{
            do{
                l_index--; //oso vriski assous sto telos phgene aristera
            }while(*(a+l_index)==1 &&(s_index!=l_index));
        }
    }
    printf("\n");
    printf("S_index %d - L_index %d\n",s_index,l_index);
    for(i=0; i<n; i++)
    {
        printf(" %d",*(a+i)) ;
    }

        if(s_index == l_index){ //sunthiki termatismoy
        return;}

    return swap(a,s_index,l_index,n); //anadromi
}

