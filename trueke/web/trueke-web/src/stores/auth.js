// import axios from 'axios';
import { defineStore } from "pinia";
import axios from "axios";

const useAuth = defineStore("auth", {
  state: () => {
    return {
      user: {},
      userEvents: [],
      token: null,
      baseURL: import.meta.env.VITE_VUE_APP_API_URL,
    };
  },
  actions: {
    async registerCollaborator(singUpCollaboratorRequest, img) {
      const uri = `${this.baseURL}/auth/register/collaborator`;

      try {
        const formData = new FormData();
        formData.append("file", img);
        formData.append(
          "body",
          new Blob([JSON.stringify(singUpCollaboratorRequest)], {
            type: "application/json",
          })
        );

        const response = await axios.post(uri, formData, {
          headers: {
            "Content-Type": "multipart/form-data", // Explicitly set Content-Type
          },
        });

        if (response.data.status) {
          console.error(
            "Registration failed:",
            response.data.message || "Unknown error"
          );
          return false;
        } else {
          console.log("Registration successful!");
          return true;
        }
      } catch (error) {
        console.error("Registration error:", error);
        return false;
      }
    },

    async login(username, password) {
      const uri = `${this.baseURL}/auth/login`;

      try {
        const response = await axios.post(
          uri,
          {
            username,
            password,
          },
          {
            headers: {
              "Content-Type": "application/json",
              Accept: "application/json",
            },
          }
        );

        if (response.data.status) {
          // Login failed (assuming 'status' indicates error in response)
          console.error(
            "Login failed:",
            response.data.message || "Unknown error"
          ); // Optional: Handle specific errors
          return false;
        } else {
          // Login successful
          this.user = response.data;
          this.token = response.data.token;
          console.log("Login successful!");
          return true;
        }
      } catch (error) {
        // Handle errors during the request
        console.error("Login error:", error);
        return false;
      }
    },

    async getUser() {
      const roles = this.user.roles.split(",");

      if (roles.includes("COLLABORATOR")) {
        const uri = `${this.baseURL}/collaborator/${this.user.id}`;

        try {
          const response = await axios.get(uri, {
            headers: {
              Authorization: `Bearer ${this.token}`,
              Accept: "application/json",
            },
          });

          if (response.data.status) {
            console.error(
              "Error fetching user data:",
              response.data.message || "Unknown error"
            );
            return false;
          } else {
            this.user = response.data;
            return true;
          }
        } catch (error) {
          console.error("Error fetching user data:", error);
          return false;
        }
      } else {
        console.log("User is not a collaborator");
      }
    },

    //COLLABORATOR
    async singUpEvent(eventId) {
      const uri = `${this.baseURL}/collaborator/${this.user.id}/event/${eventId}`;
      try {
        const response = await axios.post(
          uri,
          {},
          {
            headers: {
              Authorization: `Bearer ${this.token}`,
              Accept: "application/json",
            },
          }
        );

        if (response.data.status) {
          console.error(
            "Error while subscribing to event:",
            response.data.message || "Unknown error"
          );
          return false;
        } else {
          this.getUser()
          return true;
        }
      } catch (error) {
        console.error("Subscribing error:", error);
        return false;
      }
    },

    async unregisterFromEvent(eventId){
      const uri = `${this.baseURL}/collaborator/${this.user.id}/event/${eventId}`;
      try{
        const response = await axios.delete(uri,{
          headers:{
            Authorization: `Bearer ${this.token}`,
            Accept: 'application/json'
          }
        });
        if(response.data.status){
          console.error(
            "Error while deleting subscription:",
            response.data.message || "Unknown error"
          );
          return false;
        }else{
          this.getUser()
          return true;
        }
      }catch(error){
        console.error("Deleting subscription error:", error);
        return false
      }
    }

    //NEXT
  },
});

export default useAuth;
