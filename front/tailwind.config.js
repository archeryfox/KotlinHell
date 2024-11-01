import {postcss} from "tailwindcss";

/** @type {import('tailwindcss').Config} */
export default {
    content: ["./src/**/*.{html,js}"],
    theme: {
        extend: {},
    },
    plugins: [postcss],
}