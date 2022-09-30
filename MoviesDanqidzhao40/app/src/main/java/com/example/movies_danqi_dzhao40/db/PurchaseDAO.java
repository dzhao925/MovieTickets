package com.example.movies_danqi_dzhao40.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.movies_danqi_dzhao40.models.Purchase;

import java.util.List;

@Dao
public interface PurchaseDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertPurchase(Purchase purchase);

    @Query("SELECT * FROM purchase_table WHERE movieId = :movieId")
    public Purchase getPurchaseByMovieId(int movieId);

    @Query("SELECT * FROM purchase_table")
    public List<Purchase> getAllPurchases();

    @Update
    public void updatePurchase(Purchase purchaseToUpdate);

    @Delete
    public void deletePurchase(Purchase purchaseToDelete);

}
