const add =  (a, b) => {
    //Arguments not accessible for arrow funcs
    //console.log(arguments);
    return a + b;
}

console.log(add(50, 50));

const user = {
    name: 'Pipis',
    cities: ['Drama','Xanthi','Kavala'],
    printPlaces () {

        return this.cities.map((city)=>  city + '!');

    }
};

const multiplier = {
    numbers: [4, 7, 8, 3, 9],
    multiplyBy: 4,
    multiply () {
        return this.numbers.map((num) => 
        num + ' multiplied by ' 
        + this.multiplyBy 
        + ' gives ' 
        + num*this.multiplyBy);
    }
};

console.log(user.printPlaces());
console.log(multiplier.multiply());