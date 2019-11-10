//Nikolaos Apostolakis - icsd13012
//Konstantinos Efkarpidis - icsd15051

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

struct Stack{ //stoiva

    int length;
    int top;
    int *stck_arr;
};

struct Queue{   //oura

    int front,rear;
    int counter,length;
    int *queue_arr;
};

struct Stack *create_stack(int); //Stoiba
int s_isFull(struct Stack *);
int s_isEmpty(struct Stack *);
int s_push(struct Stack *,int);
int s_pop(struct Stack *);

struct Queue *create_queue(int);
int q_isFull(struct Queue *);
int q_isEmpty(struct Queue *);
int q_enqueue(struct Queue *,int);
int q_dequeue(struct Queue *);
int q_front(struct Queue *);
int q_rear(struct Queue *);

int main(){

    struct Stack *stack;
    struct Queue *queue;
    stack = create_stack(15);
    queue = create_queue(15);
    srand(time(NULL));
    int value,flag,flag2,tmp;
    printf("Arxiko Stack\n");
    do{     //gemisma stoivas
     value = 1 + rand()%10;
     flag = s_push(stack,value);
    }while(flag!=0);
    printf("Oyra\n");
    do{ //adeiasma stivas kai gemisma ouras
     flag = s_pop(stack);
     tmp=q_enqueue(queue,flag);
    }while(tmp!=0);
    printf("Teliko Stack\n");


    do{ //adeiasma ouras kai gemisma stoivas
    flag = q_dequeue(queue);
    tmp = s_push(stack,flag);
    }while(tmp!=0);




}

struct Stack *create_stack(int length){ //constructor stoivas

    struct Stack *stack = (struct Stack *)malloc(sizeof(struct Stack ));
    stack->length=length;
    stack->top=-1;
    stack->stck_arr=(int *)malloc(length*sizeof(int));
    return stack;
}

struct Queue *create_queue(int length){ //constructor ouras


    struct Queue *queue = (struct Queue *)malloc(sizeof(struct Queue ));
    queue->length = length;
    queue->front=0;
    queue->rear=length-1;
    queue->counter=0;
    queue->queue_arr=(int *)malloc(length*sizeof(int));
    return queue;


}

int s_isFull(struct Stack *stack){

    return stack->top == stack->length -1 ? 1 : 0;
}

int q_isFull(struct Queue *queue){

    return queue->counter == queue->length ? 1 : 0;
}

int s_isEmpty(struct Stack *stack){

    return stack-> top == -1 ? 1 : 0;
}

int q_isEmpty(struct Queue *queue){

    return queue->counter == 0 ? 1 : 0;
}

int s_push(struct Stack *stack,int value){
    //kouna ton deikti top kai vale stoixio, epestrepse 1 gia epityxia allios 0 .
    return s_isFull(stack) ? 0 : (stack->stck_arr[++stack->top] = value,printf("%d\n",stack->stck_arr[stack->top]),1);
}

int q_enqueue(struct Queue *queue, int value){
    //kouna deikti rear mexri length , auksise metriti , vale sthn oura kai epestrepse 1 allios 0
    return q_isFull(queue) ? 0 : (queue->rear = (queue->rear+1)%queue->length,queue->counter = queue ->counter + 1, queue->queue_arr[queue->rear] = value,printf("%d\n",queue->queue_arr[queue->rear]),1);
}

int s_pop(struct Stack *stack){

    return s_isEmpty(stack) ? 0 : (stack->stck_arr[stack->top--],stack->stck_arr[stack->top+1]);
}

int q_dequeue(struct Queue *queue){
    int i;  //kouna ton deikti ,se periptosi poy ftasei sto telos tha ksanapei arxi me to %. counter = metritis stoixeion
    return q_isEmpty(queue) ? -1 : (i = queue->front , queue->front = (queue->front+1)%queue->length,queue->counter = queue->counter-1, queue->queue_arr[i]);
}

int q_front(struct Queue *queue){

    return q_isEmpty(queue) ? -1 : queue->queue_arr[queue->front]; //epestrece -1 an eiani adeia
}                                                                  //alios tin thesi toy front

int q_rear(struct Queue *queue){

    return q_isEmpty(queue) ? -1 : queue->queue_arr[queue->rear]; //antistoixa me thn q_front
}



