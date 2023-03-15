const requestUrl = document.location.origin + '/admin/user/'
function sendRequestV1(method, url, body = null) {
    return new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest()
        xhr.open(method, url)
        xhr.responseType = 'json'
        xhr.setRequestHeader('Content-Type', 'application/json')
        xhr.onload = () => {
            if (xhr.status >= 400) {
                reject(xhr.response)
            } else {
                resolve(xhr.response)
            }
        }
        // xhr.send(JSON.stringify(body))
        xhr.send(body)
    })
}

function getData(id) {
    sendRequestV1('GET', requestUrl + id)
        .then(data => {
            document.querySelector('#id').setAttribute('value', data.id)
            document.querySelector('#firstname').setAttribute('value', data.firstName)
            document.querySelector('#lastname').setAttribute('value', data.lastName)
            document.querySelector('#age').setAttribute('value', data.age)
            document.querySelector('#email').setAttribute('value', data.email)
            document.querySelector('#password').setAttribute('placeholder', "")
            // data.roles.forEach(function (item) {
            //     document.querySelector('#id').setAttribute('value', data.id)
            // })
        })
        .catch(err => console.error(err))
}


const sendDataV1 = async (url, data) => {
    const response = await fetch(url, {
        method: 'POST',
        body:   data
    })
    if (!response.ok) {
        throw new Error('Ошибка по адресу ${url}, статус ошибки ${response}')
    }
    return await response.json()
}

const sendUser = () => {
    const userForm = document.querySelector('#edit-form')
    userForm.addEventListener('submit', e => {
        e.preventDefault()
        const formData = new FormData(userForm)
        // sendDataV1(document.querySelector('#edit-form').action , formData)
        sendDataV1('http://localhost:8080/admin' , formData)
            // .then(data => {
            //     console.log(data)
            // })
            // .catch(err => {
            //     console.error(err)
            // })
    })
}

document.addEventListener('DOMContentLoaded', function() {
    let editbutton = document.querySelectorAll('.edit-button')
    let deletebutton = document.querySelectorAll('.delete-button')


    editbutton.forEach(function(item){
        /* Назначаем каждой кнопке обработчик клика */
        item.addEventListener('click', function(event) {
            let button = document.querySelector('#edit-button')
            button.setAttribute('class', 'btn btn-primary')
            button.innerHTML = 'Edit user'
            document.querySelector('#staticBackdropLabel').innerHTML = 'Edit user'
            document.querySelectorAll('.editable').forEach(function(item) {item.removeAttribute('disabled')})
            document.querySelector('[name="_method"]').value = 'PATCH'
            let userid = this.getAttribute('value')
            // document.querySelector('#edit-form').action = requestUrl + userid
            getData(userid)
        })
    })

    deletebutton.forEach(function(item){
        item.addEventListener('click', function(event) {
            let button = document.querySelector('#edit-button')
            button.setAttribute('class', 'btn btn-danger')
            button.innerHTML = 'Delete user'
            document.querySelector('#staticBackdropLabel').innerHTML = 'Delete user'
            document.querySelectorAll('.editable').forEach(function(item) {item.setAttribute('disabled', true)})
            document.querySelector('[name="_method"]').value = 'DELETE'
            let userid = this.getAttribute('value')
            // document.querySelector('#edit-form').action = requestUrl + userid
            getData(userid)
        })
    })

})

