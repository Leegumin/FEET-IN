import axios from "axios";

export default axios.create({
  // baseURL: "http://192.168.0.50:8000/api",
  // 학원 컴퓨터
  baseURL: "http://localhost:8000/api",
  headers: {
    "Content-type": "application/json"
  }
});
