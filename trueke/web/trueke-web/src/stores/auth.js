import {defineStore} from 'pinia';

const useAuth = defineStore('auth',{
    state: () => {
        return {
            user: {},
            token: null,
            baseURL: import.meta.env.VITE_VUE_APP_API_URL
        }
    },
    actions: {

        async login(username, password){
            const uri = `${this.baseURL}/auth/login`
            const rawResponse = await fetch(uri, {
                method: 'POST',
                headers: {
                    'Content-Type' : 'application/json',
                    'Accept' : 'application/json'
                },
                body: JSON.stringify({
                    'username' : username,
                    'password' : password
                })
            })
            const response = await rawResponse.json();
            if(response.status){
                return false;
            }else{
                this.user = response;
                this.token = response.token;
                return true;
            }
        },

        async getUser(){
            let roles = this.user.roles.split(',')
            if(roles.includes('COLLABORATOR')){
                let uri = `${this.baseURL}/collaborator/${this.user.id}`
                const rawResponse = await fetch(uri, {
                    method: 'GET',
                    headers: {
                        'Content-Type' : 'application/json',
                        'Accept' : 'application/json',
                        'Authorization' : `Bearer ${this.token}`
                    }
                })
                const response = await rawResponse.json();
                if(response.status){
                    return false;
                }else{
                    this.user = response;
                    return true;
                }
            }else{
                console.log("NO ES COLABORATOR")
            }
        }

    }
})


export default useAuth;