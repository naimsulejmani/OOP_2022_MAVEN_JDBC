package cacttus.education.tsql.infrastructure;

import java.util.List;

//kontrata jone se cfare operacione duhet te kemi me nje tabele se paku
public interface BaseRepository<T, Tid> {
    //read-only metoda
    List<T> getAll(); //per me na i kthy te gjitha shenimet nga databaza

    T get(Tid key); //per me na kthy vetem nje shenim nga databaza sipas celesit primar

    //write-update-delete metodat
    boolean add(T item); //metoda qe duhet me u implementu per insert

    boolean modify(T item);// metoda qe duhet me u implementu per update te tabeles

    boolean remove(T item); // metoda qe duhet me u implementu me be delete

    boolean removeById(Tid key);// metoda qe duhet me u implemenmtu me fshi sipas Id-se


}
