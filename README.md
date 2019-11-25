# PopularMovies Demo Application

Создать приложение для просмотра списка популярных фильмов используя следующее API https://developers.themoviedb.org/3/
# Задача:
- Использовать: язык Kotlin, DI(Dagger/Kodein), архитектуру (MVP/MVVM), RxJava 2, Retrofit 2
- Организовать отображение списка популярных фильмов https://developers.themoviedb.org/3/movies/get-popular-movies.
- При отсутствии интернета отображать сообщение ""Нет соединения"", а при появлении сети обновлять данные автоматически. Для мониторинга состояния сети использовать RxJava 2 (https://github.com/pwittchen/ReactiveNetwork или свою реализацию)
- Запрос на список фильмов и логика с обновлением данных при отсутствии сети должны быть в одном Observable\Single\Flowable(на выбор)
Будет плюсом:
- Чистота и структурированность кода;
- Поддержка Auto-rotation - портретная и ландшафтная ориентация;
- Пагинация списка фильмов"
### Tech

The following third party libs are used
* [Android Support Libraries](https://developer.android.com/topic/libraries/support-library/) - Equivalent functionality on all android versions!
* [Architecture Components](https://developer.android.com/topic/libraries/architecture/) - ViewModel, Lifecycles with Lifecycle-aware components
* [DataBinding](https://developer.android.com/topic/libraries/data-binding//) - bind data to views
* [Dagger Android](https://google.github.io/dagger/android) - Dependency Injection
* [RxJava](https://github.com/ReactiveX/RxAndroid) - Background tasks
* [Glide](https://github.com/bumptech/glide) - Fast and efficient open image loading
* [Gson](https://github.com/google/gson) - convert Java Objects into their JSON representation and vice verca
* [ReactiveNetwork](https://github.com/pwittchen/ReactiveNetwork) - Android library listening network connection state and Internet connectivity with RxJava Observables
