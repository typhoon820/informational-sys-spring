package com.music.service.ServiceImpl;

import com.music.DAO.ArtistBandDAO;
import com.music.entity.ArtistBandEntity;
import com.music.entity.ArtistEntity;
import com.music.entity.BandEntity;
import com.music.service.ArtistBandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Service("ArtistBandService")
@Transactional

public class ArtistBandServiceImpl implements ArtistBandService {

    @Autowired
    ArtistBandDAO artistBandDAO;

    @Override
    public List<ArtistBandEntity> findAll() {
        return artistBandDAO.findAll();
    }

    @Override
    public ArtistBandEntity findById(int id) {
        return artistBandDAO.findById(id);
    }

    @Override
    public BandEntity findCurrentBand(ArtistEntity artistEntity) {
        for(ArtistBandEntity iter:  artistEntity.getBands()){
            if (iter.getLeaveDate() == null){
                return iter.getAuthorId();
            }
        }
        return null;
    }

    @Override
    public List<ArtistEntity> findCurrentArtists(BandEntity bandEntity) {
        List<ArtistEntity> res = new ArrayList<>();
        for (ArtistBandEntity ab:  bandEntity.getCurrentArtists()){
            if (ab.getAuthorId().equals(bandEntity) && ab.getLeaveDate() == null){
                res.add(ab.getArtistId());
            }
        }
        return res;
    }



    @Override
    public List<ArtistEntity> findPreviousArtists(BandEntity bandEntity) {
        List<ArtistEntity> res = new ArrayList<>();
        for (ArtistBandEntity ab:  bandEntity.getCurrentArtists()){
            if (ab.getAuthorId().equals(bandEntity) && ab.getLeaveDate() != null){
                res.add(ab.getArtistId());
            }
        }
        return res;
    }

    public void save(ArtistBandEntity artistBandEntity){
        artistBandDAO.save(artistBandEntity);
    }
    public void saveOrUpdate(ArtistBandEntity artistBandEntity){
        artistBandDAO.saveOrUpdate(artistBandEntity);
    }

    @Override
    public List<BandEntity> findPreviousBands(ArtistEntity artistEntity) {
        List<BandEntity> res = new ArrayList<>();
        for (ArtistBandEntity ab :  artistEntity.getBands()){
            if (ab.getArtistId().equals(artistEntity) && ab.getLeaveDate() != null){
                res.add(ab.getAuthorId());
            }
        }
        return res;
    }

    @Override
    public List<ArtistBandEntity> findPrevArtistBand(ArtistEntity artistEntity) {
        List<ArtistBandEntity> res = new ArrayList<>();
        for (ArtistBandEntity ab :  artistBandDAO.findAll()){
            if (ab.getArtistId().equals(artistEntity) && ab.getLeaveDate() != null){
                res.add(ab);
            }
        }
        return res;
    }

    @Override
    public List<ArtistBandEntity> findCurrentArtistBand(BandEntity bandEntity) {
        List<ArtistBandEntity> res = new ArrayList<>();
        for (ArtistBandEntity ab:  bandEntity.getCurrentArtists()){
            if (ab.getAuthorId().equals(bandEntity) && ab.getLeaveDate() == null){
                res.add(ab);
            }
        }
        return res;
    }

    @Override
    public List<ArtistBandEntity> findPreviousArtistBand(BandEntity bandEntity) {
        List<ArtistBandEntity> res = new ArrayList<>();
        for (ArtistBandEntity ab :  artistBandDAO.findAll()){
            if (ab.getAuthorId().equals(bandEntity) && ab.getLeaveDate() != null){
                res.add(ab);
            }
        }
        return res;
    }
}
