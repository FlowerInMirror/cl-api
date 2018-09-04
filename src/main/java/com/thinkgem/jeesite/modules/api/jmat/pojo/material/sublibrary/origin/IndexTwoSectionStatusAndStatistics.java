package com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.origin;

import java.io.Serializable;

/**
 * 子库起始页二段状态与材料统计
 * 
 * @author     ljc
 * @version    1.0
 * @createTime 2018-4-9 11:01:03
 */
public class IndexTwoSectionStatusAndStatistics implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 状态
	 * @author ljc
	 *
	 */
	public class Status implements Serializable {

		private static final long serialVersionUID = 1L;
		
		/**
		 * 材料统计
		 * @author ljc
		 *
		 */
		class MaterialStatistics implements Serializable {

			private static final long serialVersionUID = 1L;

		}
		/**
		 * 最近一个月
		 * @author ljc
		 *
		 */
		class LastMonth implements Serializable {
			
			private static final long serialVersionUID = 1L;
			
		}
		
		/**
		 * 成本
		 * @author ljc
		 *
		 */
		class Cost implements Serializable {
			
			private static final long serialVersionUID = 1L;
			
		}

	}
	
	/**
	 * 材料统计
	 * @author ljc
	 *
	 */
	class Statistics implements Serializable {

		private static final long serialVersionUID = 1L;
		
		/**
		 * 种类
		 * @author ljc
		 *
		 */
		class MatClass implements Serializable {

			private static final long serialVersionUID = 1L;

		}
		/**
		 * 品牌
		 * @author ljc
		 *
		 */
		class MatBrand  implements Serializable {

			private static final long serialVersionUID = 1L;

		}
		/**
		 * 平台
		 * @author ljc
		 *
		 */
		class  MatPlatform implements Serializable {

			private static final long serialVersionUID = 1L;

		}

	}

}
