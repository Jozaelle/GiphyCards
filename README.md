# GiphyCards

An app to aid the user in searching through gif's via the API from giphy.com. Each gif when clicked on displays the gifs name, description, and rating. Current default giphs shown are those under the tag 'cats' within the API.

## API Reference
API key generated using giphy API explorer for developers, using this link after creating a develper account https://developers.giphy.com/explorer?
When creating the API key, the url under 'Request Url' can be changed into what ever gif the user wants to find details on via the GiphyFinder app.
#### Get all items

```http
  GET /api/items
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Get item

```http
  GET /api/items/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of item to fetch |



## Project setup
```
npm install
```
### Compiles and minifies for production
```
npm run build
```
### Compiles and hot-reloads for development
```
npm run serve
```


### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).



