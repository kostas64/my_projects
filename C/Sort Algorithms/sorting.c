//Efkarpidis Konstantinos - icsd15051
//Nikolaos Apostolakis - icsd13012
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>

void insertion_sort(int *,int,int *);
void selection_sort(int *,int,int *);
void merge_sort(int *,int,int,int *);
void merge(int *,int,int,int,int *);
void quick_sort(int *,int,int,int *);
int partition(int *,int,int,int *);
int * create_arr(int);
void display(int *,int,int *);

int main() {
    
    int i,n,choice,again,repeat;
    int *counter;
    int count=0;
    clock_t start, end;
    double total;
    counter=&count;
    do{
        printf("Dose megethos pinaka\n");
    scanf("%d",&n);
    int *p ;
    
    
    printf("\nDialexe algorithmo taxinomisis\n");
    printf("(1)Algo Insertion-Sort\n");
    printf("(2)Algo Selection-Sort\n");
    printf("(3)Algo Merge-Sort\n");
    printf("(4)Algo Quick-Sort\n");
    scanf("%d",&choice);
    *counter=0;
    start=clock();
    for(repeat=0; repeat<100; repeat++){
        p = create_arr(n);
        printf("Epanalipsi %d \n",repeat);
    switch(choice){
        case 1:  insertion_sort(p,n,counter); break;
        case 2:  selection_sort(p,n,counter); break;
        case 3:  merge_sort(p,0,n-1,counter); break;
        case 4:  quick_sort(p,0,n-1,counter); break;
        default : break;
    }
    }
    end=clock();
    total = ((double)(end-start))/CLOCKS_PER_SEC;
    printf("Mesos # Sygkriseon %d\n",*counter/100);
    printf("Mesos Xronos taxinomisis %f",total);
    do{
    printf("\nThes na ksanadokimaseis (1 = yes , 0 = no)\n");
    scanf("%d",&again);    
    }while (again != 0 && again !=1);
    
    }while(again==1);
    
    
    
    return (EXIT_SUCCESS);
}
//Dimiourgia pinaka tuxaion
int * create_arr(int n){
    
    srand(time(NULL));
    static int *arr;
    arr = malloc(n*sizeof(int));
  
    return arr;
}

void insertion_sort(int * arr,int n,int *counter){

    int key,i,j;
    
    for (i = 1; i < n; i++) {
        key = *(arr+i);
        *counter+=1;
       
            for (j = i-1; ((j >= 0) && (key < *(arr + j))); j--){
                *(arr+j+1) = *(arr + j);
                *counter+=1;
            }
                *(arr+j+1) = key;
 
    }

}



void selection_sort(int * arr,int n,int *counter){
    
    int i,j,pos,swap;
    
    for (i = n; i > 1; i--) {
        pos = 0;
            for (j = 1; j < i; j++) { // Εύρεση max(A[0 ... i-1])
                if (*(arr+pos) < *(arr+j)){ 
                    pos = j;
                    *counter+=1;
                }
                *counter+=1;
            }
            swap=*(arr+pos);
            *(arr+pos) = *(arr+i-1);
            *(arr+i-1)=swap;
                     
    }
    
}


void merge_sort(int * arr,int left,int right,int *counter){
   
    if (left < right){ 
        //apofugi overflow
        int mid = left +(right - left) / 2; 
        *counter+=1;
        //taxinomisi aristerou kai dexiou kommatiou
        merge_sort(arr, left, mid,counter); 
        merge_sort(arr, mid+1, right,counter); 
  
        merge(arr, left, mid, right,counter); 
    } 

}

void merge (int * arr,int left,int mid, int right,int *counter){

    int i, j, k; 
    int n1 = mid - left + 1; 
    int n2 =  right - mid; 
  
    
    int L[n1], R[n2]; 
    //antigrafi dedomenon stous temp pinakes 
    for (i = 0; i < n1; i++) {
        L[i] = *(arr + left + i);
        *counter+=1;
    }
         
    for (j = 0; j < n2; j++){
             R[j] = *(arr + mid + 1 + j); 
            *counter+=1;
    } 
       
  
     /* Enosi prosorinon pinakon*/
    i = 0; // index protou upopinaka
    j = 0; // index deuterou upopinaka 
    k = left; // inden tou merged upopinaka
    while (i < n1 && j < n2){ 
        *counter+=3;
        if (L[i] <= R[j]){ 
            *(arr + k) = L[i]; 
            i++; 
            *counter+=1;
        } 
        else{ 
            *(arr + k) = R[j]; 
            j++; 
            *counter+=1;
        } 
        k++; 
    }
    
     while (i < n1){ 
        *(arr + k) = L[i]; 
        i++; 
        k++; 
        *counter+=1;
    } 
  
   //antigrafi upolipomenon stoixeion pinaka
    //ean uparxoun
    while (j < n2){ 
        *(arr + k) = R[j]; 
        j++; 
        k++; 
        *counter+=1;
    }
}

void quick_sort(int * arr,int left,int right,int *counter){
    int pi ;
    if (left < right){
        *counter+=1;
        //vriskoume pivot kai xorizoume
        pi = partition(arr,left,right,counter);
        //anadromika taxinomoume aristero kai dexi meros tou pivot
        quick_sort(arr,left,pi-1,counter);
        quick_sort(arr,pi+1,right,counter);
    
    }

}

int partition(int * arr,int left,int right,int *counter){

    int swap;
    int pivot = *(arr+right); //Orizoume os pivot to teleutaio stoixeio
    int i = left - 1,j;
    
     for (j = left; j <= right- 1; j++){
        //ean to stoixeio einai iso i mikrotero tou pivot
         *counter+=1;
        if (*(arr+j) <= pivot){
            i++;    
            //swap
            swap = *(arr+i);
            *(arr+i) = *(arr+j);
            *(arr+j) = swap;
            *counter+=1;
        }
    }
    //swap
    swap = *(arr + i + 1);
    *(arr + i + 1) = *(arr + right);
    *(arr + right) = swap;
    
    return (i + 1);
    
}

//Tin xrisimopoiousame otan dokimazame gia mikra megethi pinakon 
//gia na exetasoyme ton algorithmo
void display(int * arr,int n,int *counter){
    
    int i;
    printf("------Pinakas meta tin taxinomisi------\n");
    for(i=0; i<n; i++)
        printf("%d " ,*(arr+i));
    printf("\nArithmos Sygkriseon %d\n",*counter);
}