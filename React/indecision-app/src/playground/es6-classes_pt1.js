class Person {
    
    constructor(name = 'Unamed', Age = 0) {
      this.name = name;
      this.Age = Age;
    }
    getGretting(){
       // return this.name + ' greets you!~';
       //Avoid concats
       return `Hi , i am ${this.name}.`
    }
    getDescription(){
        return `${this.name} is ${this.Age} year(s) old!`
    }
}

class Student extends Person {
    constructor(name,Age,major){
        super(name,Age);
        this.major = major;
    }

    getMajor(){
        return !!this.major;
    }

    getDescription(){
        let description = super.getDescription();
        if (this.getMajor()){
            description +=  ` His/Her major is ${this.major}.`
        }
        return description;
    }
}
 
class Traveller extends Person {
    constructor(name,Age,homeLocation){
        super(name,Age);
        this.homeLocation = homeLocation;
    }

    getGretting(){
        let greeting = super.getGretting();
        if (this.homeLocation){
            greeting += ` I'm visiting from ${this.homeLocation}!`
        }
        return greeting;
    }
}

const me = new Traveller('Kostas',22,'Greece');
console.log(me.getGretting());

const none = new Traveller();
console.log(none.getGretting());

