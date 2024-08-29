package br.edu.ifsuldeminas.mch.trabalhocinemapdi.model.db;
import java.util.List;
import br.edu.ifsuldeminas.mch.trabalhocinemapdi.model.CinemaBranch;

public interface DAOObserver {

    default void loadOk(List<CinemaBranch> cinemaBranches) {
    }

    default void loadError() {
    }

    default void saveOk() {
    }

    default void saveError() {
    }

    default void updateOk() {
    }

    default void updateError() {
    }

    default void deleteOk() {
    }

    default void deleteError() {
    }

    //https://api.themoviedb.org/3/movie/550?api_key=24ba5b0c26143b808033dc756bca6c1d
}