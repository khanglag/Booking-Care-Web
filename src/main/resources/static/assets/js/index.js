let list = document.querySelector('.slider .list')
let items = document.querySelectorAll('.slider .list .item')
let dots = document.querySelectorAll('.slider .dots li')
let prev = document.getElementById('prev')
let next = document.getElementById('next')

let actives = 0
let lengthItems = items.length -1;

next.onclick = function (){
    if(actives +1 > lengthItems){
        actives = 0;
    }else {
        actives = actives +1
    }
    reloadSlider()
}
prev.onclick = function (){
    if(actives - 1 <0){
        actives = lengthItems
    }else {
        actives = actives -1;
    }
    reloadSlider()
}

let refreshSlider = setInterval(() => {next.click()},5000)
function  reloadSlider(){
    let checkLeft = items[actives].offsetLeft;
    list.style.left = -checkLeft + 'px';
    let lastActiveDot = document.querySelector('.slider .dots li.actives')
    lastActiveDot.classList.remove('actives')
    dots[actives].classList.add('actives')
    clearInterval(refreshSlider)
    refreshSlider = setInterval(() => {next.click()},3000)
}
dots.forEach((li,key) => {
    li.addEventListener('click',function (){
        actives = key
        reloadSlider()
    })
})