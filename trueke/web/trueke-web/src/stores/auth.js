// import axios from 'axios';
import { defineStore } from "pinia";
import axios from "axios";

const useAuth = defineStore("auth", {
  state: () => {
    return {
      user: {},
      token: null,
      baseURL: import.meta.env.VITE_VUE_APP_API_URL,
    };
  },
  actions: {
    //AXIOS
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
    //FETCH API
    // async registerCollaborator(singUpCollaboratorRequest, img) {
    //   let formData = new FormData();
    //   formData.append("file", img);
    //   formData.append(
    //     "body",
    //     new Blob([JSON.stringify(singUpCollaboratorRequest)], {
    //       type: "application/json",
    //     })
    //   );
    //   const uri = `${this.baseURL}/auth/register/collaborator`;
    //   const rawResponse = await fetch(uri, {
    //     method: "POST",
    //     body: formData,
    //   });
    //   const response = await rawResponse.json();
    //   if (response.status) {
    //     return false;
    //   } else {
    //     return true;
    //   }
    // },
    //AXIOS
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
    //FETCH API
    // async login(username, password) {
    //   const uri = `${this.baseURL}/auth/login`;
    //   const rawResponse = await fetch(uri, {
    //     method: "POST",
    //     headers: {
    //       "Content-Type": "application/json",
    //       Accept: "application/json",
    //     },
    //     body: JSON.stringify({
    //       username: username,
    //       password: password,
    //     }),
    //   });
    //   const response = await rawResponse.json();
    //   if (response.status) {
    //     return false;
    //   } else {
    //     this.user = response;
    //     this.token = response.token;
    //     return true;
    //   }
    // },
    //AXIOS
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
    //FETCH API
    // async getUser() {
    //   let roles = this.user.roles.split(",");
    //   if (roles.includes("COLLABORATOR")) {
    //     let uri = `${this.baseURL}/collaborator/${this.user.id}`;
    //     const rawResponse = await fetch(uri, {
    //       method: "GET",
    //       headers: {
    //         "Content-Type": "application/json",
    //         Accept: "application/json",
    //         Authorization: `Bearer ${this.token}`,
    //       },
    //     });
    //     const response = await rawResponse.json();
    //     if (response.status) {
    //       return false;
    //     } else {
    //       this.user = response;
    //       return true;
    //     }
    //   } else {
    //     console.log("NO ES COLABORATOR");
    //   }
    // },
  },
});

export default useAuth;
