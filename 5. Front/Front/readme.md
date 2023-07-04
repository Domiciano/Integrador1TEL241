Para hacer llamados use

```
const response = await fetch(url, {
  method: 'POST', 
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify(data)
});
const json = await response.json();
```
Donde data es un objeto</br></br>

La forma más simple es cuando hacemos un GET

```
const response = await fetch(url);
const json = await response.json();
```


https://medium.com/@ilyailin7777/how-to-deploy-javascript-app-to-github-pages-4b37b315556f
