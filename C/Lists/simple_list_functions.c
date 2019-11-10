//Nikolaos Apostolakis - icsd13012
//Konstantinos Efkarpidis - icsd15051

#include <stdio.h>
#include <stdlib.h>


struct Node{
    int value;
    struct Node *next;
};

struct Node2{
    int value;
    struct Node2 *next;
    struct Node2 *previous;
};


struct Node * AddNodeSingle(struct Node *,int,int);
void displayList(struct Node *);
struct Node * mergeSortedSingle(struct Node *,struct Node *);
struct Node * removeDuplicatesSingle(struct Node *);
int compareListSingle (struct Node *,struct Node *);
void AddNodeDouble(struct Node2 **,struct Node2 **,int ,int);
void AddNodeSortedDouble(struct Node2 **,struct Node2 **,int);
void displayList2(struct Node2 *);
void deleteNodeDouble(struct Node2 **,struct Node2 **,int);
void copyReversedDouble(struct Node2 **,struct Node2 **);
void displayCircular(struct Node2 *);
void deleteHeadCircular(struct Node2 **,struct Node2 **);
void deleteTailCircular(struct Node2 **,struct Node2 **);

int main(){

    struct Node *head;
    struct Node *head2;
    struct Node *head3;
    head=NULL;
    head2=NULL;
    head3=NULL;
    head=AddNodeSingle(head,1,0);
    head=AddNodeSingle(head,4,2);
    head=AddNodeSingle(head,5,-1);
    head=AddNodeSingle(head,6,-1);

    head2=AddNodeSingle(head2,1,0);
    head2=AddNodeSingle(head2,4,2);
    head2=AddNodeSingle(head2,5,-1);
    displayList(head);
    displayList(head2);
    head3=mergeSortedSingle(head,head2);
    displayList(head3);
    head3=removeDuplicatesSingle(head3);
    displayList(head3);
    if(compareListSingle(head,head2)==-1)
        printf("Oi listes den einai idies\n");
    else
        printf("Einai idies\n");


    struct Node2 *head4,*tail;
    head4=NULL;
    tail=NULL;
    AddNodeDouble(&head4,&tail,1,0);
    AddNodeDouble(&head4,&tail,3,-1);
    AddNodeDouble(&head4,&tail,2,2);
    AddNodeDouble(&head4,&tail,10,-1);
    AddNodeDouble(&head4,&tail,8,4);
    displayList2(head4);
    AddNodeSortedDouble(&head4,&tail,7);
    AddNodeSortedDouble(&head4,&tail,11);
    AddNodeSortedDouble(&head4,&tail,11);
    displayList2(head4);
    deleteNodeDouble(&head4,&tail,1);
    deleteNodeDouble(&head4,&tail,11);
    deleteNodeDouble(&head4,&tail,3);
    displayList2(head4);
    copyReversedDouble(&head4,&tail);

    head4->previous=tail; //Kanoyme thn diplosundedemeni lista mas kikliki
    tail->next=head4;

    displayCircular(tail);

    deleteHeadCircular(&head4,&tail);
    displayCircular(tail);
    deleteTailCircular(&head4,&tail);
    displayCircular(tail);

}


struct Node * AddNodeSingle(struct Node *head,int value,int pos){

    struct Node *nd = (struct Node*)malloc(sizeof(struct Node *));
    struct Node *temp ;
    struct Node *current ;

    int counter=1;
    if(head==NULL){
        head=nd;
        nd->next=NULL;
        nd->value=value;
      //  printf("\nO kombos me timi %d prostethike sthn arxi giati htan kai o monadikos\n",value);
    }
    else{ //Περιπτωση που υπαρχει τουλαχιστον 1 κομβος

            if(pos==0 || pos==1){
                nd->next=head;
                head=nd;
                nd->value = value;
            }
            else if(pos==-1){

                temp=head;

                while(temp!=NULL){ //Διασχιζουμε μεχρι τελος
                      current=temp;
                      temp=temp->next;
                    }

                    current->next=nd;  //Προσθετουμε κομβο
                    nd->next=NULL;
                    nd->value=value;

                }
            else{   //Opoiadipote alli thesi ektos apo arxi kai telos
                int counter=1;
                temp=head;
                while(temp!=NULL){ //Διασχιζουμε μεχρι τελος για να
                      counter++;   //δουμε ποσους κομβους εχουμε
                      temp=temp->next;
                    }
                 temp=head;
                 current=temp;
                if(pos>counter)
                    printf("\nDen yparxoun tosoi komvoi gia na prostethi\n");
                else{
                    while(counter<pos-1 || counter==pos-1){
                        current=temp;
                        temp=temp->next;
                    }
                    nd->next=current->next;
                    current->next=nd;
                    nd->value=value;
                }
            }
    }



return head;
}


void displayList(struct Node *head) //Εμφανιση στοιχειων λιστας
{
    struct Node *tmp ;
    tmp=head;
    printf("\nLista\n");
    while(tmp!=NULL){
        printf("%d ",tmp->value);
        tmp=tmp->next;
    }

    free(tmp);
}

void displayList2(struct Node2 *head) //Εμφανιση στοιχειων λιστας
{
    struct Node2 *tmp ;
    tmp=head;
    printf("\nLista\n");
    while(tmp!=NULL){
        printf("%d ",tmp->value);
        tmp=tmp->next;
    }

    free(tmp);
}


struct Node * mergeSortedSingle(struct Node * head,struct Node * head2){

    struct Node *head3,*nd ;
    struct Node *temp1 ;
    struct Node *temp2 ;

    head3=NULL;
    temp1=head;
    temp2=head2;


    while(temp1!=NULL && temp2!=NULL){ //Mexri na adiasei i mia apo tis 2

        nd = (struct Node*)malloc(sizeof(struct Node *));
        nd->next=NULL;


            if( (temp1->value) <= (temp2->value)){

                head3 = AddNodeSingle(head3,temp1->value,-1);
                temp1=temp1->next;

            }else {

                head3 = AddNodeSingle(head3,temp2->value,-1);
                temp2=temp2->next;

            }
    }

       if(temp1==NULL){ //An adiase i proti gemise thn kainourgia
                        //me oti apenime sthn 2h
          while(temp2!=NULL){

            nd = (struct Node*)malloc(sizeof(struct Node *));
            nd->next=NULL;
            head3 = AddNodeSingle(head3,temp2->value,-1);
            temp2=temp2->next;
          }
       }
       else if(temp2==NULL){ //An adiase i 2i gemise thn kainourgia
                             //me oti apenime sthn 1h
             while(temp1!=NULL){

            nd = (struct Node*)malloc(sizeof(struct Node *));
            nd->next=NULL;
            head3 = AddNodeSingle(head3,temp1->value,-1);
            temp1=temp1->next;
          }
       }
    free(temp1);
    free(temp2);


    return head3;
}

struct Node * removeDuplicatesSingle(struct Node *head){

    int flag=0; //Gia na kseri an ayto poy vrike einai to 1o.
    struct Node *temp1,*temp2,*previous;

    temp1=head;
    temp2=head;
    previous=head;
    while(temp1!=NULL){

        while(temp2!=NULL){


            if( (temp1->value == temp2 ->value) && flag){ //Αν βρεις ιδιο και ειναι το 2ο
                previous->next=temp2->next;
            }
            if( (temp1->value == temp2 ->value) && flag==0) //αν ειναι το 1ο
                flag=1;

            previous=temp2; //Προχωρα τους δεικετες
            temp2=temp2->next;
        }

        flag=0; //ξανα απο την αρχη για το επομενο
        temp2=head;
        temp1=temp1->next;
    }

return head;
}

int compareListSingle(struct Node * head,struct Node * head2){

    struct Node *temp1,*temp2;
    temp1=head;
    temp2=head2;

        while(temp1!=NULL && temp2!=NULL){ //trexoyme mia apo tis 2

            if(temp1->value != temp2 ->value) //oso vriskeis idies times proxora
                return -1;

            temp1=temp1->next;
            temp2=temp2->next;


        }
        if(temp1==NULL && temp2==NULL) //an exeis bgei kai exoun teleiosei kai oi 2 tote einai idies
            return 1;

return -1; //alios diaforetikes
}

void AddNodeDouble(struct Node2 ** head,struct Node2 ** tail,int value,int pos){

    struct Node2 *nd = (struct Node2 *)malloc(sizeof(struct Node2 *));
    struct Node2 *temp;
    nd->next=nd->previous=NULL;
    nd->value=value;

    if(*head==NULL){    //einai adeia
        *head=*tail=nd;
    }else{  //exei toulaxiston 1
        if(pos==-1){
        (*tail)->next=nd;
        nd->previous=*tail;
        *tail=nd;
        }else if(pos==0 || pos==1){ //kai mpainei sthn arxi
            (*head)->previous=nd;
            nd->next=*head;
            *head=nd;
        }else{        //kai mpainei endiamesa
            int counter=0;
                temp=*head;
                while(temp!=NULL){ //Διασχιζουμε μεχρι τελος για να
                      counter++;   //δουμε ποσους κομβους εχουμε
                      temp=temp->next;
                    }

                if(pos>counter+1)
                    printf("Den uparxoun tosoi komboi");
                else if(pos==counter+1){    //Αν δωσει την επομενη θεση απο το τελος
                    (*tail)->next=nd;
                    nd->previous=*tail;
                    *tail=nd;
                }
                else{   //αλλιως σε οποιαδηποτε αλλη θεση στην μεση
                        temp=*head;
                        counter=0;
                   while(counter!=pos-1){
                        counter++;
                        temp=temp->next;
                    }
                    nd->next=temp;
                    nd->previous=temp->previous;
                    temp->previous->next=nd;
                    temp->previous=nd;

                }
        }
    }

}

void AddNodeSortedDouble(struct Node2 ** head,struct Node2 ** tail,int value){


    struct Node2 *tmp ;
    int counter=0;
    tmp=*head;

    while( (tmp!=NULL) && (tmp->value < value)){
            counter++;
        tmp=tmp->next;
    }
    printf("counter %d",counter);
    AddNodeDouble(head,tail,value,counter+1);
}

void deleteNodeDouble(struct Node2 ** head,struct Node2 ** tail,int number){

    struct Node2 *tmp,*tmp2;

    if(*head==NULL) //adeia lista
        printf("I lista einai adeia");
    else{           //1 toulaxiston stixio
        if(*head==*tail && (*head)->value==number){ //1 stixeio kai
            free(*head);                            //auto einai o arithmos
            free(*tail);
            *head=*tail=NULL;
        }else
{
            tmp2=*head;
            tmp=*head;
            while(tmp!=NULL){
                tmp2=tmp;
                if((*head)->value==number){ //an auto pou thelo na svino einai head
                    (*head)->next->previous=NULL;
                    *head=(*head)->next;
                    free(tmp2);
                }else if((*tail)->value==number){ //an auto pou thelo na svino einai tail
                    (*tail)->previous->next=NULL;
                    *tail=(*tail)->previous;
                    free(tmp2);
                }else if(tmp->value==number){      //allios endiamesa
                    tmp2->previous->next = tmp2->next;
                    tmp2->next->previous = tmp2 ->previous;
                    free(tmp2);

                }
                tmp=tmp->next;
            }

        }

    }
}

void copyReversedDouble(struct Node2 **head,struct Node2 **tail){
     struct Node2 *temp = NULL;
     struct Node2 *current = *head;


     while (current !=  NULL) //To previous deixnei pleon sto next
     {                        //& to next deixnei sto temp , poy exei ginei previous
       temp = current->previous;
       current->previous = current->next;
       current->next = temp;
       current = current->previous;
     }

     if(temp != NULL ){ //teleutaios kombos
        *tail = *head;
        *head = temp->previous;
      }
}

void displayCircular(struct Node2 * tail){  //Προβολη κυκλικης λιστας

    struct Node2 * tmp;
    tmp=tail->next;
    printf("\nLista\n");
    while(tmp!=tail){
         printf("%d ",tmp->value);
         tmp=tmp->next;
    }
    printf("%d ",tmp->value);


}

void deleteHeadCircular(struct Node2 **head,struct Node2 **tail){


        if(*head==NULL)
            printf("I lista einai adeia");
        else if(*head==*tail){
            free(*head);
            free(*tail);
            printf("I lista adeiase");
        }else{      //διαγραφη ενδιαμεσου κομβου
            struct Node2 *tmp;
            tmp=*head;
            (*tail)->next=(*head)->next;
            *head=(*head)->next;
            (*head)->previous=*tail;
            free(tmp);
        }

}


void deleteTailCircular(struct Node2 **head,struct Node2 **tail){


        if(*head==NULL)
            printf("I lista einai adeia");
        else if(*head==*tail){
            free(*head);
            free(*tail);
            printf("I lista adeiase");
        }else{  //διαγραφη ουρας και οριοθέτηση της προτελευταιας ως ουρα
            struct Node2 *tmp;
            tmp=*tail;
            (*head)->previous=(*tail)->previous;
            *tail=(*tail)->previous;
            (*tail)->next=*head;
            free(tmp);
        }

}
