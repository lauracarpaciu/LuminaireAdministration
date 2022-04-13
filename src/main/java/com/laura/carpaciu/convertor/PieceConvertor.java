package com.laura.carpaciu.convertor;

import com.laura.carpaciu.entity.luminaire.Piece;
import com.laura.carpaciu.entity.order.PieceOrder;
import com.laura.carpaciu.entity.order.ServiceOrder;

public class PieceConvertor {

	private PieceConvertor() {

	}

	public static PieceOrder convert(Piece piece, ServiceOrder serviceOrder, int count) {
		// TODO Auto-generated method stub
		return new PieceOrder(null, piece.getPartNumber(), piece.getPartName(), count, piece.getPrice(), serviceOrder);
	}

}
