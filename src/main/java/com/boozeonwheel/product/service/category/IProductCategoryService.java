package com.boozeonwheel.product.service.category;

import com.boozeonwheel.product.objects.TreeNode;
import org.springframework.util.CollectionUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface IProductCategoryService {

  Long DEFAULT_ROOT_NODE_ID = new Long(1);

  TreeNode getFullTree(Long changesetId) throws Exception;

  TreeNode getSubTree(Long changesetId, Long nodeId) throws Exception;

  static TreeNode assembleTree(final List<TreeNode> nodes, final Long rootNodeId) {
    final Map<Long, TreeNode> mapTmp = new LinkedHashMap<>();
    for (final TreeNode current : nodes) {
      mapTmp.put(current.getMasterId(), current);
    }
    for (final TreeNode current : nodes) {
      final List<Long> parents = current.getParentId();
      if (!CollectionUtils.isEmpty(parents)) {
        for (final Long pid : parents) {
          final TreeNode parent = mapTmp.get(pid);
          if (parent != null) {
            parent.addChild(current);
            current.addParent(parent);
          }
        }
      }
    }
    return mapTmp.get(rootNodeId);
  }
}
