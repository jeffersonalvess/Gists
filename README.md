Gists
-------------

<p align="center">
  <img height="200" src="https://user-images.githubusercontent.com/7515790/108653921-6c948d00-74a6-11eb-9a10-885971411fa9.png">
</p>

Gists is an Android app that displays a list of Gists from GitHub Gist API.

What does this app do?
-------------
The app displays an infinite scroll with a list of Gists gotten from Github API, the user can open a list item to check more details about the Gist and see the files in it. Also, it's possible to save Gist owners in a favorites style list.

The app counts with:

> - Gist List containing the repository name, the owner's name and picture and the file types in it;
> - Details Page, where the user can see the full Gist or it's individual files right in the browser;
> - Favorites List containing the owners favorited by the user.

Technologies
-------------
- Android app, Kotlin language
- Gradle dependencies in Kotlin (buildSrc)
- MVVM, Multi-module
- Dependency Injection with Koin
- RxAndroid, Retrofit, Picasso
- JetPack: Paging, Room, Navigation, LiveData
- DataBinding, ViewBinding
- Light and Dark themes
- Parcial unit tests
- Error handling

App's Goal
-------------
Learn new stuff and show a little about my skills.


Content
-------------
#### Screenshots
![GistsDemo](https://user-images.githubusercontent.com/7515790/108661066-e8dc9f80-74a9-11eb-8a56-51323834b333.gif)


Team
-------------
- [Jefferson Alves](https://www.linkedin.com/in/jeffersonalvess)



License
-------------
MIT License

Copyright (c) 2021 Gists

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
