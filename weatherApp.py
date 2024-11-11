import tkinter as tk
import requests

class WeatherApp:
    def __init__(self, master):
        self.master = master
        master.title("Weather App")

        # API key from OpenWeatherMap
        self.api_key = "bd5e378503939ddaee76f12ad7a97608"

        # Create the widgets
        self.city_label = tk.Label(master, text="Enter a city:")
        self.city_entry = tk.Entry(master)
        self.search_button = tk.Button(master, text="Search", command=self.get_weather)
        self.weather_label = tk.Label(master, text="", font=("Arial", 18))

        # Layout the widgets
        self.city_label.grid(row=0, column=0, padx=10, pady=10)
        self.city_entry.grid(row=0, column=1, padx=10, pady=10)
        self.search_button.grid(row=0, column=2, padx=10, pady=10)
        self.weather_label.grid(row=1, column=0, columnspan=3, padx=10, pady=10)

    def get_weather(self):
        city = self.city_entry.get()
        url = f"http://api.openweathermap.org/data/2.5/weather?q={city}&appid={self.api_key}&units=metric"
        response = requests.get(url)
        data = response.json()

        if data["cod"] == 200:
            weather = f"{data['name']}: {data['weather'][0]['description']}, {data['main']['temp']}°C"
            self.weather_label.config(text=weather)
        else:
            self.weather_label.config(text="Error: Unable to retrieve weather data")

root = tk.Tk()
weather_app = WeatherApp(root)
root.mainloop()