package com.gmail.vitaliklancer.data.repositories

import android.util.Log
import com.gmail.vitaliklancer.data.db.dao.StudentDao
import com.gmail.vitaliklancer.data.db.entity.transformToDB
import com.gmail.vitaliklancer.data.db.entity.transformToDomain
import com.gmail.vitaliklancer.data.entity.transformToData
import com.gmail.vitaliklancer.data.entity.transformToDomain
import com.gmail.vitaliklancer.data.net.RestService
import com.gmail.vitaliklancer.domain.entity.Student
import com.gmail.vitaliklancer.domain.entity.StudentSearch
import com.gmail.vitaliklancer.domain.repositories.StudentRepository
import io.reactivex.Observable

class StudentRepositoryImp(val apiService: RestService, val studentDao: StudentDao) : StudentRepository {
    companion object {
        const val TIME_BUFFER = 60000
    }

    private var lastTimeUpdate: Long = 0

    override fun get(): Observable<List<Student>> {
        return studentDao.getAll()
                .take(1)
                .toObservable()
                .flatMap { studentDBList ->
                    if (studentDBList.isEmpty() || System.currentTimeMillis() - lastTimeUpdate > TIME_BUFFER) {
                        apiService.getStudents()
                                .doOnNext {
                                    lastTimeUpdate = System.currentTimeMillis()
                                    val list = it.map { it.transformToDB() }
                                    studentDao.deleteAll()
                                    studentDao.insert(list)
                                }
                                .map { list ->
                                    list.map { student -> student.transformToDomain() }
                                }
                                .onErrorReturn {
                                    if (studentDBList.isEmpty()) {
                                        throw it
                                    } else {
                                        studentDBList
                                                .map { it ->
                                                    it.transformToDomain()
                                                }
                                    }
                                }
                    } else {
                        Observable.just(studentDBList).map { list ->
                            list.map { student -> student.transformToDomain() }
                        }
                    }
                }
        /*var list = listOf(Student("0", "Sergey", 25, true, "https://static.boredpanda.com/blog/wp-content/uploads/2015/05/Close-and-Personal-Portraits-of-Cats-and-Dogs-Part-215__880.jpg"),
                Student("1", "Ivan", 23, true, "https://static.boredpanda.com/blog/wp-content/uploads/2015/05/Close-and-Personal-Portraits-of-Cats-and-Dogs-Part-219__880.jpg"),
                Student("2", "Olga", 22, false, "https://static.boredpanda.com/blog/wp-content/uploads/2015/05/Close-and-Personal-Portraits-of-Cats-and-Dogs-Part-216__880.jpg"),
                Student("3", "Anna", 27, false, "https://static.boredpanda.com/blog/wp-content/uploads/2015/05/Close-and-Personal-Portraits-of-Cats-and-Dogs-Part-2__880.jpg"),
                Student("4", "Pavel", 24, true, "https://static.boredpanda.com/blog/wp-content/uploads/2015/05/Close-and-Personal-Portraits-of-Cats-and-Dogs-Part-21__880.jpg"))
        return Observable.just(list)*/
    }

    override fun search(search: StudentSearch): Observable<List<Student>> {
        var list = listOf(Student("0", "Sergey", 25, true, ""),
                Student("1", "Ivan", 23, true, ""))
        return Observable.just(list)
    }

    override fun update(student: Student): Observable<Student> {
        Log.e("YYY", student.transformToData().id)
        return apiService.updateStudent(student.transformToData()).map {
            it.transformToDomain()
        }
    }

    override fun add(student: Student): Observable<Student> {
        return apiService.addStudent(student.transformToData()).map {
            it.transformToDomain()
        }
    }

    override fun delete(studentId: String): Observable<String> {
        return apiService.deleteStudent(studentId)
    }

    override fun getById(id: String): Observable<Student> {
        /*var student = Student("0", "Sergey"+id, 25, true, "https://static.boredpanda.com/blog/wp-content/uploads/2015/05/Close-and-Personal-Portraits-of-Cats-and-Dogs-Part-215__880.jpg")
        return Observable.just(student)*/
        return apiService.getStudentById(id).map {
            it.transformToDomain()
        }
    }
}