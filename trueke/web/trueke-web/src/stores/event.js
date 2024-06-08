import { defineStore } from "pinia";
import axios from 'axios';

const useEvent = defineStore("event", {
  state: () => {
    return {
      event: {},
      eventList: [],
      baseUrl: import.meta.env.VITE_VUE_APP_API_URL,
    };
  },
  actions: {
    async getAllEvents() {
      const uri = `${this.baseUrl}/event/`;

      try {
        const response = await axios.get(uri, {
          headers: {
            Accept: "application/json",
          },
        });
        if (response.data.status) {
          console.error(
            "Error fetching event data:",
            response.data.message || "Unknown error"
          );
          return false;
        } else {
          this.eventList = response.data;
          return true;
        }
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    },
  },



});

export default useEvent;
