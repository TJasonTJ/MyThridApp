package com.feicuiedu.gitdroid.utils;

import android.support.annotation.NonNull;

import com.feicuiedu.gitdroid.DB.LocalRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TJ on 2016/8/4.
 */
public class RepoConverter {
    private RepoConverter() {
    }
    public static @NonNull LocalRepo convert(@NonNull Repo repo){
        LocalRepo localRepo=new LocalRepo();
        localRepo.setAvatar_url(repo.getOwner().getAvatar());
        localRepo.setDescription(repo.getDescription());
        localRepo.setFull_name(repo.getFullname());
        localRepo.setId(repo.getId());
        localRepo.setName(repo.getName());
        localRepo.setStart_count(repo.getStarCount());
        localRepo.setFork_count(repo.getForkCount());
        localRepo.setRepoGroup(null);
        return localRepo;
    }
    public static @NonNull
    List<LocalRepo> converAll(@NonNull List<Repo> repos) {
        ArrayList<LocalRepo> localRepos = new ArrayList<LocalRepo>();
        for (Repo repo : repos) {
            localRepos.add(convert(repo));
        }
        return localRepos;
    }
}
