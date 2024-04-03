import requests
from bs4 import BeautifulSoup
import lxml

res = requests.get('https://www.plfil.com/actor')
res.raise_for_status()
soup = BeautifulSoup(res.text, 'lxml')
data = soup.find_all('img',alt="Actor Image")

print(data)
print("++++++++++++++++++++++++++++++++++++++++++++++")






