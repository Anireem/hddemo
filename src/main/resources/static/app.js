// Test file
console.log(typeof 123)

printYear(1999)

//Arrays
const cars = ['mazda', 'ford', 'mersedes']
console.log(cars)

//Objects
const person = {
    firstName: "Greg",
    lastName: "Skorpofeo",
    year: 1989,
    languages: ["Ru", "En", "De"],
    hasWife: false,
    greet: function() {
        console.log('greet')
    }
}
console.log(person.firstName)
console.log(person['lastName'])
person.hasWife = true;
console.log(person)

//Strings
const name = 'Greg';
function getAge() {
    return 31
}
const output = `my name is ${name}, age ${getAge()}`
console.log(output)
const output1 = `
    <div>this is div</div>
    <p>this is p</p>
`
console.log(output1)

// Functions
// счетчик до 5
let counter = 0;
const interval = setInterval(function() {
    if (counter === 5) {
        clearInterval(interval)
    } else {
        console.log(++counter)
    }
}, 1000)

// showObject(person)

function showObject(object) {
    alert(1)
}

function helloWorld() {
    // alert(1)
    console.log(123)
}

if (2 > 1) {
    console.log(true)
} else {
    console.log(false)
}

console.log("hello world")
function showAlert() {
    alert(person)
    alert("The button was clicked!")
}

function promptTest() {
    const lastName = prompt("введите фамилию")
}

function printYear(year) {
    console.log(year)
}